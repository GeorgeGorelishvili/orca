package com.george.orca.service;

import com.george.orca.domain.LoanPaymentEntity;
import com.george.orca.repository.LoanPaymentRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanPaymentServiceBean implements LoanPaymentService {

    private final LoanPaymentRepository loanPaymentRepository;

    @Override
    public LoanPaymentEntity get(Long id) {
        Optional<LoanPaymentEntity> optionalLoanPaymentEntity = loanPaymentRepository.findById(id);
        return new TemplateUtil<LoanPaymentEntity>().get(optionalLoanPaymentEntity);
    }

    @Override
    public LoanPaymentEntity edit(LoanPaymentEntity entity) {
        return loanPaymentRepository.save(entity);
    }

    @Override
    public List<LoanPaymentEntity> list(Long id) {
        return loanPaymentRepository.findAllByLoanId(id);
    }

    @Override
    public void delete(Long id) {
        LoanPaymentEntity loanPaymentEntity = get(id);
        loanPaymentRepository.delete(loanPaymentEntity);
    }
}
