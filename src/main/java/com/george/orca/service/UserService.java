package com.george.orca.service;

import com.george.orca.domain.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity get(Long id);

    UserEntity edit(UserEntity entity);

    List<UserEntity> list();

    void delete(Long id);
}
