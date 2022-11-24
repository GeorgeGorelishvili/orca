package com.george.orca.service;

import com.george.orca.domain.CommentEntity;
import com.george.orca.domain.PromiseEntity;

import java.util.List;

public interface PromiseService {
    PromiseEntity get(Long id);

    PromiseEntity edit(PromiseEntity entity);

    List<PromiseEntity> list(Long id);

    void delete(Long id);

}
