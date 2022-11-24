package com.george.orca.repository;

import com.george.orca.domain.CommentEntity;
import com.george.orca.domain.PromiseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PromiseRepository extends CrudRepository<PromiseEntity, Long> {

    List<PromiseEntity> findAllByLoanId(Long id);

}
