package com.george.orca.controller;

import com.george.orca.domain.LoanEntity;
import com.george.orca.service.LoanService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<LoanEntity> edit(@RequestBody LoanEntity loanEntity) {
        loanEntity = loanService.edit(loanEntity);
        return ResponseEntity.ok(loanEntity);
    }

    @GetMapping("list")
    public ResponseEntity<List<LoanEntity>> list() {
        List<LoanEntity> loans = loanService.list();
        return ResponseEntity.ok(loans);
    }

}
