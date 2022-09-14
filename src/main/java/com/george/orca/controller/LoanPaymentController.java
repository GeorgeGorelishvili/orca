package com.george.orca.controller;

import com.george.orca.domain.CommentEntity;
import com.george.orca.domain.LoanEntity;
import com.george.orca.domain.LoanPaymentEntity;
import com.george.orca.repository.LoanPaymentRepository;
import com.george.orca.service.LoanPaymentService;
import com.george.orca.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class LoanPaymentController {

    private final LoanPaymentService loanPaymentService;
    private final LoanService loanService;
    private final LoanPaymentRepository loanPaymentRepository;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<LoanPaymentEntity> add(@RequestBody LoanPaymentEntity loanPaymentEntity) {

        loanPaymentEntity = loanPaymentService.edit(loanPaymentEntity);
        loanPaymentRepository.save(loanPaymentEntity);

        MathContext mc = new MathContext(10);

        BigDecimal paymentAmount = loanPaymentEntity.getAmount();
        LoanEntity loan = loanService.get(loanPaymentEntity.getLoanId());
        BigDecimal oldAmount = loan.getAmount();


        BigDecimal newLoanAmount = oldAmount.subtract(paymentAmount, mc);

        loan.setAmount(newLoanAmount);
        if (newLoanAmount.compareTo(BigDecimal.ZERO) <= 0) {
            loan.setNullificationRequest(true);
        }
        loanService.edit(loan);

        return ResponseEntity.ok(loanPaymentEntity);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @CrossOrigin
    public LoanPaymentEntity edit(@RequestBody LoanPaymentEntity loanPaymentEntity) {
        return loanPaymentService.edit(loanPaymentEntity);
    }

    @GetMapping("/get")
    @CrossOrigin
    public List<LoanPaymentEntity> add(@RequestParam Long loanId) {
        return loanPaymentService.list(loanId);
    }


    @GetMapping("/delete")
    @CrossOrigin
    public ResponseEntity<LoanEntity> delete(@RequestParam Long id) {

        LoanPaymentEntity loanPaymentEntity = loanPaymentService.get(id);
        BigDecimal paymentAmount = loanPaymentEntity.getAmount();
        LoanEntity loan = loanService.get(loanPaymentEntity.getLoanId());
        BigDecimal oldAmount = loan.getAmount();
        MathContext mc = new MathContext(10);

        BigDecimal newLoanAmount = oldAmount.add(paymentAmount, mc);

        if (newLoanAmount.compareTo(BigDecimal.ZERO) >= 0) {
            loan.setNullified(false);
        }

        loan.setAmount(newLoanAmount);
        loanService.edit(loan);

        loanPaymentService.delete(id);

        return ResponseEntity.ok(loan);
    }

}

