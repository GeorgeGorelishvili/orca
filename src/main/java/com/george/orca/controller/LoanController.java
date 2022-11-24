package com.george.orca.controller;

import com.george.orca.domain.*;
import com.george.orca.dto.*;
import com.george.orca.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("loan")
@Slf4j
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @Autowired
    UserDetailsService userDetailsService;
    private final EmployeeService employeeService;

    private final CommentService commentService;
    private final PromiseService promiseService;
    private final AttachedFileService attachedFileService;
    private final LoanPaymentService loanPaymentService;
    private final LoanAgentHistoryService loanAgentHistoryService;
    private final AssignRequestService assignRequestService;


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
            Long assignedEmployeeId = null;
            Long visitorId = null;
            Long oldVisitorId = null;
            Long oldAssignedEmployeeId = null;


            if (loan.getVisitorId() != null) {
                visitorId = Long.parseLong(loan.getVisitorId());
            }
            if (loan.getAssignedEmployeeId() != null) {
                assignedEmployeeId = Long.parseLong(loan.getAssignedEmployeeId());
            }
            LoanEntity originalLoanEntity = loanService.get(loan.getLoanEntity().getId());
            if (originalLoanEntity.getAssignedAgent() != null && assignedEmployeeId != null) {
                oldAssignedEmployeeId = originalLoanEntity.getAssignedAgent().getId();
                if (!assignedEmployeeId.equals(oldAssignedEmployeeId)) {
                    LoanAgentHistoryEntity loanAgentHistoryEntity = LoanAgentHistoryEntity.builder()
                            .date(new Date())
                            .loanId(loanEntity.getId())
                            .employee(employeeService.get(oldAssignedEmployeeId))
                            .status("კრედიტ მენეჯერი").build();
                    loanAgentHistoryService.edit(loanAgentHistoryEntity);
                }
            }
            if (originalLoanEntity.getVisitor() != null && visitorId != null) {
                oldVisitorId = originalLoanEntity.getVisitor().getId();
                if (!visitorId.equals(oldVisitorId)) {
                    LoanAgentHistoryEntity loanAgentHistoryEntity = LoanAgentHistoryEntity
                            .builder().date(new Date())
                            .loanId(loanEntity.getId())
                            .employee(employeeService.get(oldAssignedEmployeeId))
                            .status("ვიზიტორი").build();

                    loanAgentHistoryService.edit(loanAgentHistoryEntity);
                }
            }
            if (assignedEmployeeId != null) {
                EmployeeEntity assignedEmployee = employeeService.get(assignedEmployeeId);
                loanEntity.setAssignedAgent(assignedEmployee);
            }
        }

        if (loan.getAssignedEmployeeId() != null) {
            Long assignedAgentId = Long.parseLong(loan.getAssignedEmployeeId());

            EmployeeEntity assignedAgent = employeeService.get(assignedAgentId);
            loanEntity.setAssignedAgent(assignedAgent);
        }

        if (loan.getVisitorId() != null) {
            Long visitorId = Long.parseLong(loan.getVisitorId());

            EmployeeEntity visitor = employeeService.get(visitorId);
            loanEntity.setVisitor(visitor);
        }

        List<CommentEntity> comments = commentService.list(loanEntity.getId());
        List<PromiseEntity> promises = promiseService.list(loanEntity.getId());
        List<AttachedFileEntity> attachedFileEntities = attachedFileService.list(loanEntity.getId());
        List<LoanPaymentEntity> payments = loanPaymentService.list(loanEntity.getId());
        List<LoanAgentHistoryEntity> assignHistory = loanAgentHistoryService.list(loanEntity.getId());
        AssignRequestEntity assignRequest = loanService.get(loanEntity.getId()).getAssignRequest();

        loanEntity.setComments(comments);
        loanEntity.setPromises(promises);
        loanEntity.setAttachedFileEntities(attachedFileEntities);
        loanEntity.setLoanPayments(payments);
        loanEntity.setLoanAgentHistoryEntities(assignHistory);
        loanEntity.setAssignRequest(assignRequest);


        loanEntity = loanService.edit(loanEntity);

        return ResponseEntity.ok(loanEntity);
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<LoanSearchQuery> page(Integer limit, Integer start, @RequestParam(required = false) String id, @RequestParam(required = false) String creditor, @RequestParam(required = false) String debtor, @RequestParam(required = false) String debtorIdentificator, @RequestParam(required = false) String assignedAgent, @RequestParam(required = false) BigDecimal amount, @RequestParam(required = false) BigDecimal amountStart, @RequestParam(required = false) BigDecimal amountEnd, @RequestParam(required = false) String callDateStart, @RequestParam(required = false) String callDateEnd, @RequestParam(required = false) String promiseDateStart, @RequestParam(required = false) String promiseDateEnd) {
        Boolean nullificationRequest = false;
        Boolean archived = false;
        Boolean nullified = false;
        LoanSearchQuery loanSearchQuery = loanService.page(start, limit, id, creditor, debtor, debtorIdentificator, assignedAgent, amount, amountStart, amountEnd, nullified, callDateStart, callDateEnd, promiseDateStart, promiseDateEnd, nullificationRequest, archived);
        return ResponseEntity.ok(loanSearchQuery);
    }

    @RequestMapping(value = "nullificationRequest", method = RequestMethod.GET)
    public ResponseEntity<LoanSearchQuery> nullificationRequest(Integer limit, Integer start, @RequestParam(required = false) String id, @RequestParam(required = false) String creditor, @RequestParam(required = false) String debtor, @RequestParam(required = false) String debtorIdentificator, @RequestParam(required = false) String assignedAgent, @RequestParam(required = false) BigDecimal amount,@RequestParam(required = false) BigDecimal amountStart, @RequestParam(required = false) BigDecimal amountEnd, @RequestParam(required = false) String callDateStart, @RequestParam(required = false) String callDateEnd, @RequestParam(required = false) String promiseDateStart, @RequestParam(required = false) String promiseDateEnd) {
        Boolean nullificationRequest = true;
        Boolean archived = false;
        Boolean nullified = false;

        LoanSearchQuery loanSearchQuery = loanService.page(start, limit, id, creditor, debtor, debtorIdentificator, assignedAgent, amount, amountStart, amountEnd, nullified, callDateStart, callDateEnd, promiseDateStart, promiseDateEnd, nullificationRequest, archived);
        return ResponseEntity.ok(loanSearchQuery);
    }


    @RequestMapping(value = "archive", method = RequestMethod.GET)
    public ResponseEntity<LoanSearchQuery> archive(Integer limit,
                                                   Integer start,
                                                   @RequestParam(required = false) String id,
                                                   @RequestParam(required = false) String creditor,
                                                   @RequestParam(required = false) String debtor,
                                                   @RequestParam(required = false) String debtorIdentificator,
                                                   @RequestParam(required = false) String assignedAgent,
                                                   @RequestParam(required = false) BigDecimal amount,
                                                   @RequestParam(required = false) String callDateStart,
                                                   @RequestParam(required = false) String callDateEnd,
                                                   @RequestParam(required = false) String promiseDateStart,
                                                   @RequestParam(required = false) String promiseDateEnd,
                                                   @RequestParam(required = false) String status) {
        boolean nullified = true;
        boolean archived = true;
        boolean nullificationRequest = false;
        LoanSearchQuery loanSearchQuery = loanService.getArchive(start, limit, id, creditor, debtor, debtorIdentificator, assignedAgent, amount, nullified, callDateStart, callDateEnd, promiseDateStart, promiseDateEnd, nullificationRequest, archived, status);
        return ResponseEntity.ok(loanSearchQuery);
    }

    @RequestMapping(value = "assignRequest", method = RequestMethod.GET)
    public ResponseEntity<LoanSearchQuery> getAssignRequestLoans(Integer limit, Integer start, @RequestParam String assignRequestReason, @RequestParam(required = false) String id, @RequestParam(required = false) String creditor, @RequestParam(required = false) String debtor, @RequestParam(required = false) String debtorIdentificator, @RequestParam(required = false) String assignedAgent, @RequestParam(required = false) BigDecimal amount) {
        Boolean nullificationRequest = false;
        Boolean archived = false;
        Boolean nullified = false;
        LoanSearchQuery loanSearchQuery = loanService.getAssignRequestLoans(start, limit, id, assignRequestReason, creditor, debtor, debtorIdentificator, assignedAgent, amount, nullified, nullificationRequest, archived);
        return ResponseEntity.ok(loanSearchQuery);
    }

    @PostMapping("reassign")
    public ResponseEntity<String> reassign(@RequestBody ReassignLoansDTO loans) {
        EmployeeEntity employee = null;
        EmployeeEntity visitor = null;
        if (loans.getAgentId() != null) {
            employee = employeeService.get(loans.getAgentId());
        } else if (loans.getVisitorId() != null) {
            visitor = employeeService.get(loans.getVisitorId());
        }

        for (Long loanId : loans.getLoanIds()) {
            LoanEntity loan = loanService.get(loanId);


            if (employee != null) {
                if (loan.getAssignedAgent() != null) {
                    LoanAgentHistoryEntity loanAgentHistoryEntity = LoanAgentHistoryEntity.builder()
                            .date(new Date())
                            .loanId(loan.getId())
                            .employee(employeeService.get(loan.getAssignedAgent().getId()))
                            .status("კრედიტ მენეჯერი").build();
                    loanAgentHistoryService.edit(loanAgentHistoryEntity);
                }

                loan.setAssignRequest(null);
                loan.setAssignedAgent(employee);
                loanService.edit(loan);

            } else if (visitor != null) {
                if (loan.getVisitor() != null) {
                    LoanAgentHistoryEntity loanAgentHistoryEntity = LoanAgentHistoryEntity
                            .builder()
                            .date(new Date())
                            .loanId(loan.getId())
                            .employee(employeeService.get(loan.getVisitor().getId()))
                            .status("ვიზიტორი").build();
                    loanAgentHistoryService.edit(loanAgentHistoryEntity);
                }

                loan.setAssignRequest(null);
                loan.setVisitor(visitor);
                loanService.edit(loan);
            }
        }

        return ResponseEntity.ok("ok");
    }

    @PostMapping("cancelAssignRequests")
    public ResponseEntity<String> reassign(@RequestBody List<Long> loanIds) {
        for (Long loanId : loanIds) {
            LoanEntity loan = loanService.get(loanId);
            loan.setAssignRequest(null);
            loanService.edit(loan);
        }
        return ResponseEntity.ok("ok");
    }

    @PostMapping("moveAssignRequestsToArchive")
    public ResponseEntity<String> moveAssignRequestsToArchive(@RequestBody AssignRequestToArchiveDTO assignRequestToArchiveDTO) {
        for (Long loanId : assignRequestToArchiveDTO.getLoanIds()) {
            LoanEntity loan = loanService.get(loanId);
            loan.setArchiveStatus(assignRequestToArchiveDTO.getArchiveStatus());
            loan.setArchiveReason(assignRequestToArchiveDTO.getArchiveReason());
            loan.setArchived(true);
            loan.setAssignRequest(null);
            loanService.edit(loan);
        }
        return ResponseEntity.ok("ok");
    }

    @PostMapping("returnNullifyingRequestsToAgent")
    public ResponseEntity<String> returnNullifyingRequestsToAgent(@RequestBody List<Long> loanIds) {
        for (Long loanId : loanIds) {
            LoanEntity loan = loanService.get(loanId);
            loan.setNullificationRequest(false);
            loanService.edit(loan);
        }
        return ResponseEntity.ok("ok");
    }

    @PostMapping("moveNullifyingRequestsToArchive")
    public ResponseEntity<String> moveNullifyingRequestsToArchive(@RequestBody AssignRequestToArchiveDTO assignRequestToArchiveDTO) {
        for (Long loanId : assignRequestToArchiveDTO.getLoanIds()) {
            LoanEntity loan = loanService.get(loanId);
            loan.setArchiveStatus(assignRequestToArchiveDTO.getArchiveStatus());
            loan.setArchiveReason(assignRequestToArchiveDTO.getArchiveReason());
            loan.setArchived(true);
            loan.setNullificationRequest(false);
            loanService.edit(loan);
        }
        return ResponseEntity.ok("ok");
    }

}