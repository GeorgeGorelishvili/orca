package com.george.orca.service;

import com.george.orca.domain.LoanEntity;
import com.george.orca.repository.LoanRepository;
import com.george.orca.repository.LoanSortingRepository;
import com.george.orca.utils.TemplateUtil;
//import jdk.incubator.vector.VectorOperators;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class LoanServiceBean implements LoanService {

    private final LoanRepository loanRepository;

    private final LoanSortingRepository loanSortingRepository;

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
    public List<LoanEntity> list(BigDecimal amount, Integer limit, Integer start) {
//        Iterable<LoanEntity> iterableLoanEntities = loanRepository.findAll();
        Pageable paging = PageRequest.of(limit, start);

        List<LoanEntity> loanEntity = loanSortingRepository.loanWithPaging(amount, paging);

        return loanEntity;
    }

    @Override
    public void delete(Long loanId) {
        LoanEntity loanEntity = get(loanId);
        loanRepository.delete(loanEntity);
    }
}
