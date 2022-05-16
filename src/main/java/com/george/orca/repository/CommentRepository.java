package com.george.orca.repository;

import com.george.orca.domain.CommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByLoanId(Long id);

}
