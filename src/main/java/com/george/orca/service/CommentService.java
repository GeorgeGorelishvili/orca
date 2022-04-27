package com.george.orca.service;

import com.george.orca.domain.CommentEntity;

import java.util.List;

public interface CommentService {
    CommentEntity get(Long id);

    CommentEntity edit(CommentEntity entity);

    List<CommentEntity> list();

    void delete(Long id);
}




