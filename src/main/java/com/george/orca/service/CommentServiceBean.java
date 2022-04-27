package com.george.orca.service;

import com.george.orca.domain.CommentEntity;
import com.george.orca.repository.CommentRepository;
import com.george.orca.utils.TemplateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentServiceBean implements CommentService{

    private final CommentRepository commentRepository;


        @Override
        public CommentEntity get(Long id) {
            Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(id);
            return new TemplateUtil<CommentEntity>().get(optionalCommentEntity);
        }

        @Override
        public CommentEntity edit(CommentEntity entity) {
            return commentRepository.save(entity);
        }

        @Override
        public List<CommentEntity> list() {
            Iterable<CommentEntity> iterableCommentEntities = commentRepository.findAll();
            return new TemplateUtil<CommentEntity>().list(iterableCommentEntities);
        }

        @Override
        public void delete(Long id) {
            CommentEntity commentEntity = get(id);
            commentRepository.delete(commentEntity);
        }

}


