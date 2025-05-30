package com.foroclone.foroclone.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foroclone.foroclone.domain.dto.UpvoteDto;
import com.foroclone.foroclone.domain.entities.UpvoteEntity;
import com.foroclone.foroclone.mappers.Mapper;
import com.foroclone.foroclone.services.UpvoteService;

@RestController
public class UpvoteController {
    
    private UpvoteService upvoteService;

    private Mapper<UpvoteEntity, UpvoteDto> upvoteMapper;

    public UpvoteController(UpvoteService upvoteService, Mapper<UpvoteEntity, UpvoteDto> upvoteMapper) {
        this.upvoteService = upvoteService;
        this.upvoteMapper = upvoteMapper;

    }

    @PostMapping(path = "/upvotes")
    public ResponseEntity<UpvoteDto> createUpvote(@RequestBody UpvoteDto upvote) {
        UpvoteEntity upvoteEntity = upvoteMapper.mapFrom(upvote);
        UpvoteEntity savedUpvoteEntity = upvoteService.save(upvoteEntity);
        return new ResponseEntity<>(upvoteMapper.mapTo(savedUpvoteEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/upvotes")
    public List<UpvoteDto> listUpvotes() {
        List<UpvoteEntity> upvotes = upvoteService.findAll();
        return upvotes.stream()
                .map(upvoteMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/upvotes/{id}")
    public ResponseEntity<UpvoteDto> getUpvote(@PathVariable() Long id) {
        Optional<UpvoteEntity> foundUpvote = upvoteService.findOne(id);
        return foundUpvote.map(upvoteEntity -> {
            UpvoteDto upvoteDto = upvoteMapper.mapTo(upvoteEntity);
            return new ResponseEntity<>(upvoteDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


    @GetMapping("/upvotes/by-user/{userId}")
    public ResponseEntity<List<UpvoteDto>> getUpvotesByUser(@PathVariable Long userId) {
        List<UpvoteEntity> upvotes = upvoteService.findByCreatorId(userId);

        if (upvotes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UpvoteDto> response = upvotes.stream()
                .map(upvoteMapper::mapTo)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/upvotes/by-post/{postId}")
    public ResponseEntity<List<UpvoteDto>> getUpvotesByPost(@PathVariable Long postId) {
        List<UpvoteEntity> upvotes = upvoteService.findByPostId(postId);

        if (upvotes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UpvoteDto> response = upvotes.stream()
                .map(upvoteMapper::mapTo)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/upvotes/by-comment/{commentId}")
    public ResponseEntity<List<UpvoteDto>> getUpvotesByComment(@PathVariable Long commentId) {
        List<UpvoteEntity> upvotes = upvoteService.findByCommentId(commentId);

        if (upvotes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UpvoteDto> response = upvotes.stream()
                .map(upvoteMapper::mapTo)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/upvotes/{id}")
    public ResponseEntity<UpvoteEntity> deleteUpvote(@PathVariable() Long id) {
        upvoteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
