package com.foroclone.foroclone.services;
import com.foroclone.foroclone.domain.entities.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostService {

    PostEntity save(PostEntity PostEntity);

    List<PostEntity> findAll();

    Optional<PostEntity> findOne(Long id);

    boolean isExists(Long id);

    PostEntity partialUpdate(Long id, PostEntity userEntity);

    void delete(Long id);

    List<PostEntity> findByCreatorId(Long userId);

    List<PostEntity> findByCommunityId(Long communityId);

    Long count();
}
