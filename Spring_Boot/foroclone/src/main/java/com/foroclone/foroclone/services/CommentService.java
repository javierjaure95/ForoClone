package com.foroclone.foroclone.services;
import com.foroclone.foroclone.domain.entities.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    CommentEntity save(CommentEntity CommentEntity);

    List<CommentEntity> findAll();

    Optional<CommentEntity> findOne(Long id);

    boolean isExists(Long id);

    CommentEntity partialUpdate(Long id, CommentEntity userEntity);

    void delete(Long id);

    List<CommentEntity> findByCreatorId(Long userId);

    List<CommentEntity> findByPostId(Long postId);

    List<CommentEntity> findByParentId(Long parentId);

    Long count();

}
