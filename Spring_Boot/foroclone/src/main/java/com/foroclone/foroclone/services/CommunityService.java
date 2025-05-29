package com.foroclone.foroclone.services;
import com.foroclone.foroclone.domain.entities.CommunityEntity;

import java.util.List;
import java.util.Optional;

public interface CommunityService {

    CommunityEntity save(CommunityEntity CommunityEntity);

    List<CommunityEntity> findAll();

    Optional<CommunityEntity> findOne(Long id);

    boolean isExists(Long id);

    CommunityEntity partialUpdate(Long id, CommunityEntity userEntity);

    void delete(Long id);

    List<CommunityEntity> findByCreatorId(Long userId);

}
