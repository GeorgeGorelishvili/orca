package com.george.orca.service;

import com.george.orca.domain.LoanPaymentEntity;
import com.george.orca.dto.LoanPaymentsSearchQuery;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface LoanPaymentService {

    LoanPaymentEntity get(Long id);

    LoanPaymentEntity edit(LoanPaymentEntity entity);

    List<LoanPaymentEntity> list(Long id);

    LoanPaymentsSearchQuery page(Integer start,
                                 Integer limit,
                                 String creditor,
                                 String id,
                                 String debtor,
                                 String debtorIdentificator,
                                 String assignedAgent,
                                 Long amountStart,
                                 Long amountEnd,
                                 String dateStart,
                                 String dateEnd);


    List<LoanPaymentEntity> deniedList(Long id);

    void delete(Long id);
}
