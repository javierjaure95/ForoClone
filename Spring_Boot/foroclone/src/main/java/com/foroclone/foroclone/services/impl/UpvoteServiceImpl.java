package com.foroclone.foroclone.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.foroclone.foroclone.domain.entities.UpvoteEntity;
import com.foroclone.foroclone.repositories.UpvoteRepository;
import com.foroclone.foroclone.services.UpvoteService;

@Service
public class UpvoteServiceImpl implements UpvoteService{
        
    private UpvoteRepository upvoteRepository;

    public UpvoteServiceImpl(UpvoteRepository upvoteRepository) {
        this.upvoteRepository = upvoteRepository;
    }


    @Override
    public UpvoteEntity save(UpvoteEntity upvoteEntity) {

        return upvoteRepository.save(upvoteEntity);
    }


    @Override
    public List<UpvoteEntity> findAll() {
        return StreamSupport.stream(upvoteRepository
                        .findAll()
                        .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UpvoteEntity> findOne(Long id) {
        return upvoteRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return upvoteRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        upvoteRepository.deleteById(id);
    }

    @Override
    public List<UpvoteEntity> findByCreatorId(Long userId) {
        return upvoteRepository.findByCreatorId(userId);
    }

    @Override
    public List<UpvoteEntity> findByPostId(Long postId) {
        return upvoteRepository.findByPostId(postId);
    }

    @Override
    public List<UpvoteEntity> findByCommentId(Long commentId) {
        return upvoteRepository.findByCommentId(commentId);
    }

    @Override
    public Long count() {
        return upvoteRepository.count();
    }


}
