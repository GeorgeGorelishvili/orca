package com.george.orca.service;

import com.george.orca.domain.LoanEntity;
import com.george.orca.dto.LoanSearchQuery;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
