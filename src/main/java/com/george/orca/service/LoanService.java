package com.george.orca.service;

import com.george.orca.domain.LoanEntity;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {

    LoanEntity get(Long id);

    LoanEntity edit(LoanEntity entity);

    Page<LoanEntity> page(Integer start, Integer limit);

    void delete(Long id);
}
