package com.george.orca.service;

import com.george.orca.domain.OrganizationEntity;
import com.george.orca.dto.LoanSearchQuery;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface OrganizationService {

    OrganizationEntity get(Long id);
    OrganizationEntity getOrganizationByID(String code);

    OrganizationEntity edit(OrganizationEntity entity);


    Page<OrganizationEntity> page(Integer start,
                                  Integer limit);

    void delete(Long id);
}
