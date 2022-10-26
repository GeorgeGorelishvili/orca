package com.george.orca.service;

import com.george.orca.domain.LoanAgentHistoryEntity;

import java.util.List;

public interface LoanAgentHistoryService {

    LoanAgentHistoryEntity get(Long id);

    LoanAgentHistoryEntity edit(LoanAgentHistoryEntity entity);


    List<LoanAgentHistoryEntity> list(Long loanId);

    void delete(Long id);

}
