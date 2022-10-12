package com.george.orca.repository;

import com.george.orca.domain.AssignRequestEntity;
import com.george.orca.domain.AssignRequestReasonsEntity;
import com.george.orca.domain.CommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AssignRequestReasonsRepository extends CrudRepository<AssignRequestReasonsEntity, Long> {


}
