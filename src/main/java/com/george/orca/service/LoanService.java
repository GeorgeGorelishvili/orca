package com.george.orca.service;

import com.george.orca.domain.LoanEntity;
import com.george.orca.dto.LoanSearchQuery;

import java.math.BigDecimal;

public interface LoanService {

    LoanEntity get(Long id);

    LoanEntity edit(LoanEntity entity);

    LoanSearchQuery page(Integer start,
                         Integer limit,
                         String id,
                         String creditor,
                         String debtor,
                         String debtorIdentificator,
                         String assignedAgent,
                         BigDecimal amount,
                         Boolean nullified,
                         String callDateStart,
                         String callDateEnd,
                         String promiseDateStart,
                         String promiseDateEnd,
                         Boolean nullificationRequest,
                         Boolean archived);

    LoanSearchQuery getAssignRequestLoans(Integer start,
                                          Integer limit,
                                          String id,
                                          String assignRequestReason,
                                          String creditor,
                                          String debtor,
                                          String debtorIdentificator,
                                          String assignedAgent,
                                          BigDecimal amount,
                                          Boolean nullified,
                                          Boolean nullificationRequest,
                                          Boolean archived);

    LoanSearchQuery getArchive(Integer start,
                               Integer limit,
                               String id,
                               String creditor,
                               String debtor,
                               String debtorIdentificator,
                               String assignedAgent,
                               BigDecimal amount,
                               Boolean nullified,
                               String callDateStart,
                               String callDateEnd,
                               String promiseDateStart,
                               String promiseDateEnd,
                               Boolean nullificationRequest,
                               Boolean archived);

    void delete(Long id);
}
