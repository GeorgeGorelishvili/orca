package com.george.orca.dto;

import com.george.orca.domain.LoanEntity;
import com.george.orca.domain.LoanPaymentEntity;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Data
public class LoanPromisesSearchQuery {
    BigDecimal totalAmount;
    BigDecimal totalPromiseAmount;
    Page<LoanEntity> loanEntities;
}
