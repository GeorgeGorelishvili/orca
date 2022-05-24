package com.george.orca.controller;

import com.george.orca.domain.CommentEntity;
import com.george.orca.domain.LoanEntity;
import com.george.orca.domain.LoanPaymentEntity;
import com.george.orca.repository.LoanPaymentRepository;
import com.george.orca.service.LoanPaymentService;
import com.george.orca.service.LoanService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<LoanPaymentEntity> add(@RequestBody LoanPaymentEntity loanPaymententity){

        loanPaymententity = loanPaymentService.edit(loanPaymententity);
        loanPaymentRepository.save(loanPaymententity);

        MathContext mc = new MathContext(10);

        BigDecimal paymentAmount = loanPaymententity.getAmount();
        LoanEntity loan = loanService.get(loanPaymententity.getLoanId());
        BigDecimal oldAmount = loan.getAmount();


        BigDecimal newLoanAmount =  oldAmount.subtract(paymentAmount, mc);

        loan.setAmount(newLoanAmount);
        loanService.edit(loan);

        return ResponseEntity.ok(loanPaymententity);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @CrossOrigin
    public LoanPaymentEntity edit(@RequestBody LoanPaymentEntity commentEntity) {
        return loanPaymentService.edit(commentEntity);
    }

    @GetMapping("/get")
    @CrossOrigin
    public List<LoanPaymentEntity> add(@RequestParam Long loanId){
        return loanPaymentService.list(loanId);
    }



    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @CrossOrigin
    public void delete(@RequestParam Long commentId) {
        loanPaymentService.delete(commentId);
    }


}

