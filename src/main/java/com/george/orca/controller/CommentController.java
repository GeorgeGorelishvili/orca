package com.george.orca.controller;

import com.george.orca.domain.*;
import com.george.orca.dto.LoanEditDTO;
import com.george.orca.repository.UserRepository;
import com.george.orca.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserRepository userRepository;

    private final LoanService loanService;
    private final AssignRequestService assignRequestService;
    private final PromiseService promiseService;
    private final AssignRequestReasonsService assignRequestReasonsService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<CommentEntity> add(@RequestBody CommentEntity commentEntity) {

        Date date = new Date();


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = userRepository.findByUsername(authentication.getName());
        EmployeeEntity employee = currentUser.getEmployeeEntity();

        if (Objects.isNull(employee)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body(commentEntity);
        }

        String author = employee.getFirstName() + " " + employee.getLastName();


        commentEntity.setEmployee(employee);
        commentEntity.setCreateDate(date);
        commentEntity.setAuthor(author);

        if (commentEntity.getCallDate() != null) {
            LoanEntity loanEntity = loanService.get(commentEntity.getLoanId());
            loanEntity.setCallDate(commentEntity.getCallDate());
            loanService.edit(loanEntity);
        }

        if (commentEntity.getPromiseDate() != null) {
            LoanEntity loanEntity = loanService.get(commentEntity.getLoanId());
            loanEntity.setPromiseDate(commentEntity.getPromiseDate());
            PromiseEntity promiseEntity = new PromiseEntity().builder()
                    .loanId(loanEntity.getId())
                    .date(commentEntity.getPromiseDate())
                    .author(author).build();
            promiseService.edit(promiseEntity);
            loanService.edit(loanEntity);
        }
        if (commentEntity.getAssignRequestReason() != null) {
            LoanEntity loanEntity = loanService.get(commentEntity.getLoanId());
            AssignRequestReasonsEntity assignRequestEntity = assignRequestReasonsService.get(commentEntity.getAssignRequestReason());
            AssignRequestEntity assignRequest = AssignRequestEntity.builder()
                    .loanId(commentEntity.getLoanId())
                    .reason(assignRequestEntity)
                    .comment(commentEntity.getComment())
                    .date(date)
                    .author(author)
                    .build();
            assignRequest = assignRequestService.edit(assignRequest);

            loanEntity.setAssignRequest(assignRequest);
            loanService.edit(loanEntity);
        }
        commentEntity = commentService.edit(commentEntity);
        return ResponseEntity.ok(commentEntity);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @CrossOrigin
    public CommentEntity edit(@RequestBody CommentEntity commentEntity) {
        Date date = new Date();


        commentEntity.setCreateDate(date);
        return commentService.edit(commentEntity);
    }

    @GetMapping("get")
    @CrossOrigin
    public List<CommentEntity> add(@RequestParam Long loanId) {
        return commentService.list(loanId);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam Long commentId) {
        commentService.delete(commentId);
    }
}
