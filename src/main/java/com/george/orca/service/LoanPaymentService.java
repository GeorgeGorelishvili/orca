package com.george.orca.service;

import com.george.orca.domain.LoanPaymentEntity;

import java.util.List;

public interface LoanPaymentService {

    LoanPaymentEntity get(Long id);

    LoanPaymentEntity edit(LoanPaymentEntity entity);

    List<LoanPaymentEntity> list();

    void delete(Long id);
}
