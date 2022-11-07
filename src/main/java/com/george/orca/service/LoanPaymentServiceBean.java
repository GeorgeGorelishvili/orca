package com.george.orca.service;

import com.george.orca.domain.EmployeeEntity;
import com.george.orca.domain.LoanPaymentEntity;
import com.george.orca.domain.UserEntity;
import com.george.orca.dto.LoanPaymentsSearchQuery;
import com.george.orca.repository.LoanPaymentRepository;
import com.george.orca.repository.LoanSortingRepository;
import com.george.orca.repository.PaymentSortingRepository;
import com.george.orca.repository.UserRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
public class LoanPaymentServiceBean implements LoanPaymentService {

    private final LoanPaymentRepository loanPaymentRepository;
    private final PaymentSortingRepository paymentSortingRepository;
    private final LoanSortingRepository loanSortingRepository;
    private final UserRepository userRepository;

    @Override
    public LoanPaymentEntity get(Long id) {
        Optional<LoanPaymentEntity> optionalLoanPaymentEntity = loanPaymentRepository.findById(id);
        return new TemplateUtil<LoanPaymentEntity>().get(optionalLoanPaymentEntity);
    }

    @Override
    public LoanPaymentEntity edit(LoanPaymentEntity entity) {
//        Date createDate = new Date();
//
//        entity.setDate(createDate);

        return loanPaymentRepository.save(entity);
    }

    @Override
    public List<LoanPaymentEntity> list(Long id) {
        return loanPaymentRepository.findAllByLoanId(id);
    }

    @Override
    public LoanPaymentsSearchQuery page(Integer start, Integer limit, String id, String creditor, String debtor, String debtorIdentificator, String assignedAgent, Long amountStart, Long amountEnd, String dateStart, String dateEnd, Boolean withCheck) {
        Long localId = null;
        BigDecimal convertedAmountStart = null;
        BigDecimal convertedAmountEnd = null;

        if (id != null) {
            localId = Long.valueOf(id);
        }

        if (amountStart != null) {
            convertedAmountStart = BigDecimal.valueOf(amountStart);
        }

        if (amountEnd != null) {
            convertedAmountEnd = BigDecimal.valueOf(amountEnd);
        }
        Pageable paging = PageRequest.of(start, limit);

        LoanPaymentsSearchQuery loanPaymentsSearchQuery = new LoanPaymentsSearchQuery();

        Date formattedDateStart = null, formattedDateEnd = null;

        if (!(dateStart == null) && !(dateEnd == null)) {
            try {
                formattedDateStart = new SimpleDateFormat("dd/MM/yyyy").parse(dateStart);
                formattedDateEnd = new SimpleDateFormat("dd/MM/yyyy").parse(dateEnd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        loanPaymentsSearchQuery.setLoanEntities(loanSortingRepository.findEntitiesWithPayments(localId, creditor, debtor, debtorIdentificator, convertedAmountStart, convertedAmountEnd, formattedDateStart, formattedDateEnd, assignedAgent, withCheck, paging));

        List<BigDecimal> totalAmount = loanSortingRepository.findEntitiesWithPaymentsSum(localId, creditor, debtor, debtorIdentificator, convertedAmountStart, convertedAmountEnd, formattedDateStart, formattedDateEnd, assignedAgent, withCheck);
        List<BigDecimal> totalCount = loanSortingRepository.findEntitiesWithPaymentsCount(localId, creditor, debtor, debtorIdentificator, convertedAmountStart, convertedAmountEnd, formattedDateStart, formattedDateEnd, assignedAgent, withCheck);
        List<BigDecimal> totalWithCheckCount = loanSortingRepository.findEntitiesWithPaymentsWithCheckAmount(localId, creditor, debtor, debtorIdentificator, convertedAmountStart, convertedAmountEnd, formattedDateStart, formattedDateEnd, assignedAgent, withCheck);
        List<BigDecimal> totalPaidExtra = loanSortingRepository.findPaidExtra(localId, creditor, debtor, debtorIdentificator, convertedAmountStart, convertedAmountEnd, formattedDateStart, formattedDateEnd, assignedAgent, withCheck);

        loanPaymentsSearchQuery.setTotalAmount(totalAmount.get(0));
        loanPaymentsSearchQuery.setPaymentCount(totalCount.get(0));
        loanPaymentsSearchQuery.setWithCheckAmount(totalWithCheckCount.get(0));
        loanPaymentsSearchQuery.setTotalExtraPaid(totalPaidExtra.get(0));


        return loanPaymentsSearchQuery;

    }

    @Override
    public List<LoanPaymentEntity> deniedList(Long id) {
        return loanPaymentRepository.findDeniedPayments(id);
    }


    @Override
    public void delete(Long id) {
        LoanPaymentEntity loanPaymentEntity = get(id);
        loanPaymentRepository.delete(loanPaymentEntity);
    }
}
