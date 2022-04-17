package com.george.orca.service;

import com.george.orca.domain.LoanEntity;

import java.util.List;

public interface LoanService {

    LoanEntity get(Long id);

    LoanEntity edit(LoanEntity entity);

    List<LoanEntity> list();

    void delete(Long id);
}
