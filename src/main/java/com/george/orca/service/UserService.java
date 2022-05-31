package com.george.orca.service;

import com.george.orca.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserEntity get(Long id);

    UserEntity edit(UserEntity entity);

    List<UserEntity> list();

    void delete(Long id);

    UserEntity getUserByUsername(String username);


}
