package com.george.orca.controller;
import com.george.orca.domain.*;
import com.george.orca.dto.LoanEditDTO;
import com.george.orca.service.EmployeeService;
import com.george.orca.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final EmployeeService employeeService;

    @GetMapping("get")
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


        loanService.edit(loanEntity);
        ResponseEntity.ok().body(loanEntity);

        return ResponseEntity.ok(loanEntity);

    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    @CrossOrigin

//    public Page<LoanEntity> find(
//            @RequestParam(required = false) String debtorOrganizationId,
//            @RequestParam(required = false) String lastName,
//            @RequestParam(required = false) String personalNumber,
//            @RequestParam Integer offset,
//            @RequestParam Integer limit) {
//        if(debtorOrganizationId==null) {
//            OrganizationEntity searchOrg = new OrganizationEntity();
//        }
//        LoanEntity searchParams = LoanEntity.builder()
//                .comments(debtorOrganizationId)
//                .lastName(lastName)
//                .personalNumber(personalNumber)
//                .build();
//        return loanService.page(searchParams, limit, offset);
//    }
    public ResponseEntity<Page<LoanEntity>> page(Integer limit, Integer start) {
        Page<LoanEntity> loans = loanService.page(start, limit);
        return ResponseEntity.ok(loans);
    }

}
