package com.george.orca.service;

import com.george.orca.domain.AttachedFileEntity;
import com.george.orca.domain.CommentEntity;

import java.util.List;

public interface AttachedFileService {

    AttachedFileEntity get(Long id);

    AttachedFileEntity edit(AttachedFileEntity entity);

    List<AttachedFileEntity> list(Long id);

    void delete(Long id);

}




