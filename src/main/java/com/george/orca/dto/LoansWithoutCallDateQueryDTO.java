package com.george.orca.dto;

import com.george.orca.domain.LoanEntity;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Data

public class LoansWithoutCallDateQueryDTO {

    BigDecimal totalAmount;
    Page<LoanEntity> loanEntities;
}
