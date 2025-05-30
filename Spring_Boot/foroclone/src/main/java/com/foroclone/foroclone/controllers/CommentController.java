package com.foroclone.foroclone.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foroclone.foroclone.domain.dto.CommentDto;
import com.foroclone.foroclone.domain.entities.CommentEntity;
import com.foroclone.foroclone.mappers.Mapper;
import com.foroclone.foroclone.services.CommentService;

@RestController
public class CommentController {
    
    private CommentService commentService;

    private Mapper<CommentEntity, CommentDto> commentMapper;

    public CommentController(CommentService commentService, Mapper<CommentEntity, CommentDto> commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;

    }

    @PostMapping(path = "/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment) {
        CommentEntity commentEntity = commentMapper.mapFrom(comment);
        CommentEntity savedCommentEntity = commentService.save(commentEntity);
        return new ResponseEntity<>(commentMapper.mapTo(savedCommentEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/comments")
    public List<CommentDto> listComments() {
        List<CommentEntity> comments = commentService.findAll();
        return comments.stream()
                .map(commentMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/comments/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable() Long id) {
        Optional<CommentEntity> foundComment = commentService.findOne(id);
        return foundComment.map(commentEntity -> {
            CommentDto commentDto = commentMapper.mapTo(commentEntity);
            return new ResponseEntity<>(commentDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


    @GetMapping("/comments/by-user/{userId}")
    public ResponseEntity<List<CommentDto>> getCommentsByUser(@PathVariable Long userId) {
        List<CommentEntity> comments = commentService.findByCreatorId(userId);

        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CommentDto> response = comments.stream()
                .map(commentMapper::mapTo)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/comments/by-post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Long postId) {
        List<CommentEntity> comments = commentService.findByPostId(postId);

        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CommentDto> response = comments.stream()
                .map(commentMapper::mapTo)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/comments/by-comments/{parentId}")
    public ResponseEntity<List<CommentDto>> getCommentsByParent(@PathVariable Long parentId) {
        List<CommentEntity> comments = commentService.findByParentId(parentId);

        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CommentDto> response = comments.stream()
                .map(commentMapper::mapTo)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PutMapping(path = "/comments/{id}")
    public ResponseEntity<CommentDto> fullUpdateComment(
            @PathVariable() Long id,
            @RequestBody CommentDto commentDto) {

        if(!commentService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        commentDto.setId(id);
        CommentEntity commentEntity = commentMapper.mapFrom(commentDto);
        CommentEntity savedCommentEntity = commentService.save(commentEntity);
        return new ResponseEntity<>(
                commentMapper.mapTo(savedCommentEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/comments/{id}")
    public ResponseEntity<CommentDto> partialUpdate(
            @PathVariable() Long id,
            @RequestBody CommentDto commentDto
    ) {
        if(!commentService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        CommentEntity commentEntity = commentMapper.mapFrom(commentDto);
        CommentEntity updatedComment = commentService.partialUpdate(id, commentEntity);
        return new ResponseEntity<>(
                commentMapper.mapTo(updatedComment),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/comments/{id}")
    public ResponseEntity<CommentEntity> deleteComment(@PathVariable() Long id) {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
