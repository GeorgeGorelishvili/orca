package com.george.orca.service;

import com.george.orca.domain.EmployeeEntity;
import com.george.orca.domain.LoanEntity;
import com.george.orca.domain.UserEntity;
import com.george.orca.dto.LoanSearchQuery;
import com.george.orca.repository.LoanRepository;
import com.george.orca.repository.LoanSortingRepository;
import com.george.orca.repository.UserRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    public LoanSearchQuery page(Integer start, Integer limit, String id, String creditor, String debtor, String debtorIdentificator, String assignedAgent, BigDecimal amount, Boolean nullified, String callDateStart, String callDateEnd, String promiseDateStart, String promiseDateEnd, Boolean nullificationRequest, Boolean archived) {
        LoanSearchQuery loanSearchQuery = new LoanSearchQuery();
        Pageable paging = PageRequest.of(start, limit);

        Date formattedCallDateStart = null;
        Date formattedCallDateEnd = null;
        Date formattedPromiseDateStart = null;
        Date formattedPromiseDateEnd = null;


        Long localId = null;
        if (id != null) {
            localId = Long.valueOf(id);
        }


        //დალოგინებული იუზერი

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = userRepository.findByUsername(authentication.getName());
        EmployeeEntity currentEmployee = currentUser.getEmployeeEntity();


        //თარიღების ფორმატი
        if (!(callDateStart == null) && !(callDateEnd == null)) {
            try {
                formattedCallDateStart = new SimpleDateFormat("dd/MM/yyyy").parse(callDateStart);
                formattedCallDateEnd = new SimpleDateFormat("dd/MM/yyyy").parse(callDateEnd);
            } catch (ParseException e) {
                e.printStackTrace();

            }
        } else if (!(promiseDateStart == null) && !(promiseDateEnd == null)) {
            try {
                formattedPromiseDateStart = new SimpleDateFormat("dd/MM/yyyy").parse(promiseDateStart);
                formattedPromiseDateEnd = new SimpleDateFormat("dd/MM/yyyy").parse(promiseDateEnd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (currentEmployee.getEmployeePosition().getId() == 1) {
            loanSearchQuery.setLoanEntities(loanSortingRepository.findLoanEntitiesByAssignedAgent(currentEmployee, localId, creditor, debtor, debtorIdentificator, amount, nullified, formattedCallDateStart, formattedCallDateEnd, formattedPromiseDateStart, formattedPromiseDateEnd, assignedAgent, archived, nullificationRequest, paging));
            List<BigDecimal> totalAmount = loanSortingRepository.getSumForAgent(currentEmployee, localId, creditor, debtor, debtorIdentificator, amount, nullified, formattedCallDateStart, formattedCallDateEnd, formattedPromiseDateStart, formattedPromiseDateEnd, assignedAgent, archived, nullificationRequest);
            loanSearchQuery.setTotalAmount(totalAmount.get(0));
        } else {
            loanSearchQuery.setLoanEntities(loanSortingRepository.findLoanEntities(localId, creditor, debtor, debtorIdentificator, amount, nullified, formattedCallDateStart, formattedCallDateEnd, formattedPromiseDateStart, formattedPromiseDateEnd, assignedAgent, nullificationRequest, archived, paging));
            List<BigDecimal> totalAmount = loanSortingRepository.getSum(localId, creditor, debtor, debtorIdentificator, amount, nullified, formattedCallDateStart, formattedCallDateEnd, formattedPromiseDateStart, formattedPromiseDateEnd, assignedAgent, archived, nullificationRequest);
            loanSearchQuery.setTotalAmount(totalAmount.get(0));
        }
        return loanSearchQuery;
    }


    public LoanSearchQuery getArchive(Integer start, Integer limit, String id, String creditor, String debtor, String debtorIdentificator, String assignedAgent, BigDecimal amount, Boolean nullified, String callDateStart, String callDateEnd, String promiseDateStart, String promiseDateEnd, Boolean nullificationRequest, Boolean archived) {
        Long localId = null;
        LoanSearchQuery loanSearchQuery = new LoanSearchQuery();
        Pageable paging = PageRequest.of(start, limit);

        Date formattedCallDateStart = null;
        Date formattedCallDateEnd = null;
        Date formattedPromiseDateStart = null;
        Date formattedPromiseDateEnd = null;

        if (id != null) {
            localId = Long.valueOf(id);
        }


        //დალოგინებული იუზერი

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = userRepository.findByUsername(authentication.getName());
        EmployeeEntity currentEmployee = currentUser.getEmployeeEntity();


        //თარიღების ფორმატი
        if (!(callDateStart == null) && !(callDateEnd == null)) {
            try {
                formattedCallDateStart = new SimpleDateFormat("dd/MM/yyyy").parse(callDateStart);
                formattedCallDateEnd = new SimpleDateFormat("dd/MM/yyyy").parse(callDateEnd);
            } catch (ParseException e) {
                e.printStackTrace();

            }
        } else if (!(promiseDateStart == null) && !(promiseDateEnd == null)) {
            try {
                formattedPromiseDateStart = new SimpleDateFormat("dd/MM/yyyy").parse(promiseDateStart);
                formattedPromiseDateEnd = new SimpleDateFormat("dd/MM/yyyy").parse(promiseDateEnd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        loanSearchQuery.setLoanEntities(loanSortingRepository.findArchiveLoanEntities(localId, creditor, debtor, debtorIdentificator, amount, nullified, formattedCallDateStart, formattedCallDateEnd, formattedPromiseDateStart, formattedPromiseDateEnd, assignedAgent, nullificationRequest, archived, paging));
        List<BigDecimal> totalAmount = loanSortingRepository.getArchiveSum(localId, creditor, debtor, debtorIdentificator, amount, nullified, formattedCallDateStart, formattedCallDateEnd, formattedPromiseDateStart, formattedPromiseDateEnd, assignedAgent, archived, nullificationRequest);
        loanSearchQuery.setTotalAmount(totalAmount.get(0));

        return loanSearchQuery;
    }


    @Override
    public LoanSearchQuery getAssignRequestLoans(Integer start, Integer limit, String id, String assignRequestReason, String creditor, String debtor, String debtorIdentificator, String assignedAgent, BigDecimal amount, Boolean nullified, Boolean nullificationRequest, Boolean archived) {
        LoanSearchQuery loanSearchQuery = new LoanSearchQuery();
        Pageable paging = PageRequest.of(start, limit);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = userRepository.findByUsername(authentication.getName());
        EmployeeEntity currentEmployee = currentUser.getEmployeeEntity();
        Long employeeId = currentEmployee.getId();

        if (currentEmployee.getEmployeePosition().getId() > 7) {
            employeeId = null;
        }

        Long localId = null;
        if (id != null) {
            localId = Long.valueOf(id);
        }

        Long assignRequestReasonId = null;
        if (assignRequestReason != null) {
            assignRequestReasonId = Long.valueOf(assignRequestReason);
        }


        loanSearchQuery.setLoanEntities(loanSortingRepository.getAssignRequestLoans(localId, employeeId, assignRequestReasonId, creditor, debtor, debtorIdentificator, amount, nullified, assignedAgent, archived, nullificationRequest, paging));
        List<BigDecimal> totalAmount = loanSortingRepository.getSumForAssignRequestLoans(localId, employeeId, assignRequestReasonId, creditor, debtor, debtorIdentificator, amount, nullified, assignedAgent, archived, nullificationRequest);
        loanSearchQuery.setTotalAmount(totalAmount.get(0));

        return loanSearchQuery;
    }


    @Override
    public void delete(Long loanId) {
        LoanEntity loanEntity = get(loanId);
        loanRepository.delete(loanEntity);
    }
}
