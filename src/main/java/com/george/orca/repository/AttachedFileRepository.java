package com.george.orca.repository;

import com.george.orca.domain.AttachedFileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttachedFileRepository extends CrudRepository<AttachedFileEntity, Long> {
    List<AttachedFileEntity> findAllByLoanId(Long id);

}


