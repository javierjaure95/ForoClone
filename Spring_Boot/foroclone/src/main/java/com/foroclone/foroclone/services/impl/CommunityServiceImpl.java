package com.foroclone.foroclone.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.foroclone.foroclone.domain.entities.CommunityEntity;
import com.foroclone.foroclone.repositories.CommunityRepository;
import com.foroclone.foroclone.services.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService{
        
    private CommunityRepository communityRepository;

    public CommunityServiceImpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }


    @Override
    public CommunityEntity save(CommunityEntity communityEntity) {

        return communityRepository.save(communityEntity);
    }


    @Override
    public List<CommunityEntity> findAll() {
        return StreamSupport.stream(communityRepository
                        .findAll()
                        .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CommunityEntity> findOne(Long id) {
        return communityRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return communityRepository.existsById(id);
    }

    @Override
    public CommunityEntity partialUpdate(Long id, CommunityEntity communityEntity) {
        communityEntity.setId(id);

        return communityRepository.findById(id).map(existingCommunity -> {
            Optional.ofNullable(communityEntity.getName()).ifPresent(existingCommunity::setName);
            Optional.ofNullable(communityEntity.getTitle()).ifPresent(existingCommunity::setTitle);
            Optional.ofNullable(communityEntity.getDescription()).ifPresent(existingCommunity::setDescription);
            return communityRepository.save(existingCommunity);
        }).orElseThrow(() -> new RuntimeException("Community does not exist"));
    }

    @Override
    public void delete(Long id) {
        communityRepository.deleteById(id);
    }

    @Override
    public List<CommunityEntity> findByCreatorId(Long userId) {
        return communityRepository.findByCreatorId(userId);
    }

    @Override
    public Long count() {
        return communityRepository.count();
    }

}
