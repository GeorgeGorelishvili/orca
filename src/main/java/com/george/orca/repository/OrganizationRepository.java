package com.george.orca.repository;

import com.george.orca.domain.OrganizationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<OrganizationEntity, Long> {

    Optional<OrganizationEntity> findOrganizationEntityByCadastrialCode(String code);

    @Query("SELECT o FROM OrganizationEntity o WHERE 1=1 AND " +
            "(:orgName IS NULL OR o.orgName  LIKE %:orgName%) AND " +
            "(:cadastrialCode IS NULL OR o.cadastrialCode  LIKE %:cadastrialCode%)")
    Page<OrganizationEntity> findPagedOrganizations(String orgName, String cadastrialCode, Pageable paging);

}
