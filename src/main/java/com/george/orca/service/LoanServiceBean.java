package com.george.orca.service;

import com.george.orca.domain.LoanEntity;
import com.george.orca.repository.LoanRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class LoanServiceBean implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public LoanEntity get(Long loanId) {
        Optional<LoanEntity> optionalLoanEntity = loanRepository.findById(loanId);
        return new TemplateUtil<LoanEntity>().get(optionalLoanEntity);
    }

    @Override
    public LoanEntity edit(LoanEntity entity) {
        return loanRepository.save(entity);
    }

    @Override
    public List<LoanEntity> list() {
        Iterable<LoanEntity> iterableLoanEntities = loanRepository.findAll();
        return new TemplateUtil<LoanEntity>().list(iterableLoanEntities);
    }

    @Override
    public void delete(Long loanId) {
        LoanEntity loanEntity = get(loanId);
        loanRepository.delete(loanEntity);
    }
}
