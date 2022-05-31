package com.george.orca.repository;

import com.george.orca.domain.LoanEntity;
import com.george.orca.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("select l from UserEntity l WHERE l.username = :username")
    UserEntity findByUsername(String username);

}
