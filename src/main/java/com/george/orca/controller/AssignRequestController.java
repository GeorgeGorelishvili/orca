package com.george.orca.controller;

import com.george.orca.domain.*;
import com.george.orca.dto.LoanEditDTO;
import com.george.orca.repository.UserRepository;
import com.george.orca.service.AssignRequestReasonsService;
import com.george.orca.service.AssignRequestService;
import com.george.orca.service.CommentService;
import com.george.orca.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("assignRequest")
@RequiredArgsConstructor
public class AssignRequestController {

    private final UserRepository userRepository;
    private final AssignRequestService assignRequestService;
    private final LoanService loanService;
    private final AssignRequestReasonsService assignRequestReasonsServiceService;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<LoanEntity> add(@RequestParam Long assignRequestReasonId,
                                          @RequestParam Long loanId,
                                          @RequestParam String comment) {

        Date date = new Date();


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = userRepository.findByUsername(authentication.getName());
        EmployeeEntity employee = currentUser.getEmployeeEntity();

        String author = employee.getFirstName() + " " + employee.getLastName();

        AssignRequestReasonsEntity assignRequestReasonsEntity = assignRequestReasonsServiceService.get(assignRequestReasonId);


        AssignRequestEntity assignRequest = AssignRequestEntity.builder()
                .loanId(loanId)
                .reason(assignRequestReasonsEntity)
                .comment(comment)
                .date(date)
                .author(author)
                .build();
        assignRequest = assignRequestService.edit(assignRequest);

        LoanEntity loan = loanService.get(loanId);
        loan.setAssignRequest(assignRequest);
        loanService.edit(loan);

        return ResponseEntity.ok(loan);
    }

}
