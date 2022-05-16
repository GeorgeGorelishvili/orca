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
import java.util.List;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
public class LoanPaymentController {

    private final LoanPaymentService loanPaymentService;
    private final LoanService loanService;
    private final LoanPaymentRepository loanPaymentRepository;


    @GetMapping("add")
    @CrossOrigin
    public ResponseEntity<LoanPaymentEntity> add(@RequestParam BigDecimal amount,
                                                 @RequestParam Long loanId,
                                                 @RequestParam Boolean payed) {

        LoanPaymentEntity loanPaymententity = LoanPaymentEntity.builder()
                .payed(payed)
                .amount(amount)
                .loanId(loanId)
                .build();
        loanPaymententity = loanPaymentService.edit(loanPaymententity);
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

