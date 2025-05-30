package com.foroclone.foroclone.services;
import com.foroclone.foroclone.domain.entities.UpvoteEntity;

import java.util.List;
import java.util.Optional;

public interface UpvoteService {

    UpvoteEntity save(UpvoteEntity UpvoteEntity);

    List<UpvoteEntity> findAll();

    Optional<UpvoteEntity> findOne(Long id);

    boolean isExists(Long id);

    void delete(Long id);

    List<UpvoteEntity> findByCreatorId(Long userId);

    List<UpvoteEntity> findByPostId(Long postId);

    List<UpvoteEntity> findByCommentId(Long commentId);

    Long count();

}
