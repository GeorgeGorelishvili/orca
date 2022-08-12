package com.george.orca.controller;

import com.george.orca.domain.*;
import com.george.orca.dto.LoanEditDTO;
import com.george.orca.dto.LoanSearchQuery;
import com.george.orca.repository.UserRepository;
import com.george.orca.service.*;
import io.github.classgraph.Resource;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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
    private final AttachedFileService attachedFileService;
    private final LoanPaymentService loanPaymentService;


    @GetMapping("get")
    @CrossOrigin
    public ResponseEntity<LoanEntity> get(@RequestParam(name = "id") Long loanId) {
        LoanEntity loanEntity = loanService.get(loanId);
        return ResponseEntity.ok(loanEntity);
    }

    @PostMapping("add")
    public ResponseEntity<LoanEntity> add(@RequestBody LoanEntity loan) {
        loan = loanService.edit(loan);
        return ResponseEntity.ok(loan);
    }

    @PostMapping("edit")
    @CrossOrigin
    public ResponseEntity<LoanEntity> edit(@RequestBody LoanEditDTO loan) {

        LoanEntity loanEntity = loan.getLoanEntity();


        if (loan.getAssignedEmployeeId() != null) {
            Long assignedEmployeeId = Long.parseLong(loan.getAssignedEmployeeId());

            EmployeeEntity assignedEmployee = employeeService.get(assignedEmployeeId);
            loanEntity.setAssignedAgent(assignedEmployee);

        }

        if (loan.getVisitorId() != null) {
            Long visitorId = Long.parseLong(loan.getVisitorId());

            EmployeeEntity visitor = employeeService.get(visitorId);
            loanEntity.setVisitor(visitor);
        }

        List<CommentEntity> comments = commentService.list(loanEntity.getId());
        List<AttachedFileEntity> attachedFileEntities = attachedFileService.list(loanEntity.getId());
        List<LoanPaymentEntity> payments = loanPaymentService.list(loanEntity.getId());

        loanEntity.setComments(comments);
        loanEntity.setAttachedFileEntities(attachedFileEntities);
        loanEntity.setLoanPayments(payments);


        loanEntity = loanService.edit(loanEntity);

        return ResponseEntity.ok(loanEntity);
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<LoanSearchQuery> page(Integer limit, Integer start,
                                                @RequestParam(required = false) String id,
                                                @RequestParam(required = false) String creditor,
                                                @RequestParam(required = false) String debtor,
                                                @RequestParam(required = false) String debtorIdentificator,
                                                @RequestParam(required = false) String assignedAgent,
                                                @RequestParam(required = false) BigDecimal amount,
                                                @RequestParam(required = false) Boolean nullified,
                                                @RequestParam(required = false) String callDateStart,
                                                @RequestParam(required = false) String callDateEnd,
                                                @RequestParam(required = false) String promiseDateStart,
                                                @RequestParam(required = false) String promiseDateEnd) {
        LoanSearchQuery loanSearchQuery = loanService.page(start, limit, id, creditor, debtor, debtorIdentificator, assignedAgent, amount, nullified, callDateStart, callDateEnd, promiseDateStart, promiseDateEnd);
        return ResponseEntity.ok(loanSearchQuery);
    }
}