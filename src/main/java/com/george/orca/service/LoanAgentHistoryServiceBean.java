package com.george.orca.service;

import com.george.orca.domain.LoanAgentHistoryEntity;
import com.george.orca.domain.LoanPaymentEntity;
import com.george.orca.repository.LoanAgentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanAgentHistoryServiceBean implements LoanAgentHistoryService {

    private final LoanAgentHistoryRepository loanAgentHistoryRepository;


    @Override
    public LoanAgentHistoryEntity edit(LoanAgentHistoryEntity entity) {
        return loanAgentHistoryRepository.save(entity);
    }

    @Override
    public LoanAgentHistoryEntity get(Long id) {
        return null;
    }

    @Override
    public List<LoanAgentHistoryEntity> list(Long id) {
        return loanAgentHistoryRepository.findAllByLoanId(id);
    }


    @Override
    public void delete(Long id) {

    }
}
