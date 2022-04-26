package com.george.orca.service;

import com.george.orca.domain.LoanEntity;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {

    LoanEntity get(Long id);

    LoanEntity edit(LoanEntity entity);

    List<LoanEntity> list(BigDecimal amount, Integer limit, Integer start);

    void delete(Long id);
}
