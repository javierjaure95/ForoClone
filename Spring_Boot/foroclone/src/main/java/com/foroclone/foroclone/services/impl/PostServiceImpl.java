package com.foroclone.foroclone.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.foroclone.foroclone.domain.entities.PostEntity;
import com.foroclone.foroclone.repositories.PostRepository;
import com.foroclone.foroclone.services.PostService;

@Service
public class PostServiceImpl implements PostService{
        
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public PostEntity save(PostEntity postEntity) {

        return postRepository.save(postEntity);
    }


    @Override
    public List<PostEntity> findAll() {
        return StreamSupport.stream(postRepository
                        .findAll()
                        .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PostEntity> findOne(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return postRepository.existsById(id);
    }

    @Override
    public PostEntity partialUpdate(Long id, PostEntity postEntity) {
        postEntity.setId(id);

        return postRepository.findById(id).map(existingPost -> {
            
            Optional.ofNullable(postEntity.getTitle()).ifPresent(existingPost::setTitle);
            Optional.ofNullable(postEntity.getContent()).ifPresent(existingPost::setContent);
            return postRepository.save(existingPost);
        }).orElseThrow(() -> new RuntimeException("Post does not exist"));
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<PostEntity> findByCreatorId(Long userId) {
        return postRepository.findByCreatorId(userId);
    }

    @Override
    public List<PostEntity> findByCommunityId(Long communityId) {
        return postRepository.findByCommunityId(communityId);
    }


    @Override
    public Long count() {
        return postRepository.count();
    }

}
