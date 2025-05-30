package com.foroclone.foroclone.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.foroclone.foroclone.domain.entities.CommentEntity;
import com.foroclone.foroclone.repositories.CommentRepository;
import com.foroclone.foroclone.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
        
    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public CommentEntity save(CommentEntity commentEntity) {

        return commentRepository.save(commentEntity);
    }


    @Override
    public List<CommentEntity> findAll() {
        return StreamSupport.stream(commentRepository
                        .findAll()
                        .spliterator(),
                        false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CommentEntity> findOne(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return commentRepository.existsById(id);
    }

    @Override
    public CommentEntity partialUpdate(Long id, CommentEntity commentEntity) {
        commentEntity.setId(id);

        return commentRepository.findById(id).map(existingComment -> {
            
            Optional.ofNullable(commentEntity.getContent()).ifPresent(existingComment::setContent);
            return commentRepository.save(existingComment);
        }).orElseThrow(() -> new RuntimeException("Comment does not exist"));
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentEntity> findByCreatorId(Long userId) {
        return commentRepository.findByCreatorId(userId);
    }

    @Override
    public List<CommentEntity> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public List<CommentEntity> findByParentId(Long parentId) {
        return commentRepository.findByParentId(parentId);
    }

    @Override
    public Long count() {
        return commentRepository.count();
    }

}
