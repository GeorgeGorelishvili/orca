package com.george.orca.service;

import com.george.orca.domain.EmployeeEntity;
import com.george.orca.domain.LoanEntity;
import com.george.orca.domain.UserEntity;
import com.george.orca.repository.LoanRepository;
import com.george.orca.repository.LoanSortingRepository;
import com.george.orca.repository.UserRepository;
import com.george.orca.utils.TemplateUtil;
//import jdk.incubator.vector.VectorOperators;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanServiceBean implements LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

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
    public Page<LoanEntity> page(Integer start,
                                 Integer limit,
                                 String id,
                                 String creditor,
                                 String debtor,
                                 String debtorIdentificator,
                                 String assignedAgent,
                                 BigDecimal amount) {
        Long localId = null;
        Pageable paging = PageRequest.of(start, limit);
        Page<LoanEntity> loanEntity;

        if (id != null) {
            localId = Long.valueOf(id);
        }

        if (amount != null) {

        }

        //დალოგინებული იუზერი

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = userRepository.findByUsername(authentication.getName());
        EmployeeEntity currentEmployee = currentUser.getEmployeeEntity();


        if (currentEmployee.getEmployeePosition().getId() == 1) {
            loanEntity = loanSortingRepository.findLoanEntitiesByAssignedAgent(currentEmployee, localId, creditor, debtor, debtorIdentificator, amount, assignedAgent, paging);
        } else {
            loanEntity = loanSortingRepository.findLoanEntities(localId, creditor, debtor, debtorIdentificator, amount, assignedAgent, paging);
        }
        return loanEntity;
    }

    @Override
    public void delete(Long loanId) {
        LoanEntity loanEntity = get(loanId);
        loanRepository.delete(loanEntity);
    }
}
