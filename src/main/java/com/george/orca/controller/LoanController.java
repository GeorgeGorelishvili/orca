package com.george.orca.controller;
import com.george.orca.domain.*;
import com.george.orca.dto.LoanEditDTO;
import com.george.orca.service.CommentService;
import com.george.orca.service.EmployeeService;
import com.george.orca.service.LoanService;
import io.github.classgraph.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @Autowired
    UserDetailsService userDetailsService;
    private final EmployeeService employeeService;

    private final CommentService commentService;


    @GetMapping("get")
    @CrossOrigin
    public ResponseEntity<LoanEntity> get(@RequestParam(name = "id") Long loanId) {
        LoanEntity loanEntity = loanService.get(loanId);
        return ResponseEntity.ok(loanEntity);
    }

    @GetMapping("add")
    public ResponseEntity<LoanEntity> add(@RequestParam BigDecimal amount,
                                          @RequestParam Date createDate,
                                          @RequestParam Date incomeDate,
                                          @RequestParam Date startDate,
                                          @RequestParam Date endDate) {
        LoanEntity loan = LoanEntity.builder()
                .amount(amount)
                .createDate(createDate)
                .incomeDate(incomeDate)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        loan = loanService.edit(loan);
        return ResponseEntity.ok(loan);
    }

    @PostMapping("edit")
    @CrossOrigin
    public ResponseEntity<LoanEntity> edit(@RequestBody LoanEditDTO loan) {

        LoanEntity loanEntity = loan.getLoanEntity();


        if(loan.getAssignedEmployeeId() != null) {
            Long assignedEmployeeId = Long.parseLong(loan.getAssignedEmployeeId());

            EmployeeEntity assignedEmployee = employeeService.get(assignedEmployeeId);
            loanEntity.setAssignedAgent(assignedEmployee);

        }

        if(loan.getVisitorId() != null){
            Long visitorId = Long.parseLong(loan.getVisitorId());

            EmployeeEntity visitor = employeeService.get(visitorId);
            loanEntity.setVisitor(visitor);
        }

        List<CommentEntity> comments = commentService.list(loanEntity.getId());

        loanEntity.setComments(comments);


        loanService.edit(loanEntity);
        ResponseEntity.ok().body(loanEntity);

        return ResponseEntity.ok(loanEntity);

    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    @CrossOrigin
    public ResponseEntity<Page<LoanEntity>> page(Integer limit, Integer start) {

        Page<LoanEntity> loans = loanService.page(start, limit);
        return ResponseEntity.ok(loans);
    }
}
