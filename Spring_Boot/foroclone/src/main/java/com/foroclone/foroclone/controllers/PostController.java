package com.foroclone.foroclone.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foroclone.foroclone.domain.dto.PostDto;
import com.foroclone.foroclone.domain.entities.PostEntity;
import com.foroclone.foroclone.mappers.Mapper;
import com.foroclone.foroclone.services.PostService;

@RestController
public class PostController {
    
    private PostService postService;

    private Mapper<PostEntity, PostDto> postMapper;

    public PostController(PostService postService, Mapper<PostEntity, PostDto> postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;

    }

    @PostMapping(path = "/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post) {
        PostEntity postEntity = postMapper.mapFrom(post);
        PostEntity savedPostEntity = postService.save(postEntity);
        return new ResponseEntity<>(postMapper.mapTo(savedPostEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/posts")
    public List<PostDto> listPosts() {
        List<PostEntity> posts = postService.findAll();
        return posts.stream()
                .map(postMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable() Long id) {
        Optional<PostEntity> foundPost = postService.findOne(id);
        return foundPost.map(postEntity -> {
            PostDto postDto = postMapper.mapTo(postEntity);
            return new ResponseEntity<>(postDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }


    @GetMapping("/posts/by-user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Long userId) {
        List<PostEntity> posts = postService.findByCreatorId(userId);

        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<PostDto> response = posts.stream()
                .map(postMapper::mapTo)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts/by-community/{communityId}")
    public ResponseEntity<List<PostDto>> getPostsByCommunity(@PathVariable Long communityId) {
        List<PostEntity> posts = postService.findByCommunityId(communityId);

        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<PostDto> response = posts.stream()
                .map(postMapper::mapTo)
                .toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PutMapping(path = "/posts/{id}")
    public ResponseEntity<PostDto> fullUpdatePost(
            @PathVariable() Long id,
            @RequestBody PostDto postDto) {

        if(!postService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        postDto.setId(id);
        PostEntity postEntity = postMapper.mapFrom(postDto);
        PostEntity savedPostEntity = postService.save(postEntity);
        return new ResponseEntity<>(
                postMapper.mapTo(savedPostEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/posts/{id}")
    public ResponseEntity<PostDto> partialUpdate(
            @PathVariable() Long id,
            @RequestBody PostDto postDto
    ) {
        if(!postService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        PostEntity postEntity = postMapper.mapFrom(postDto);
        PostEntity updatedPost = postService.partialUpdate(id, postEntity);
        return new ResponseEntity<>(
                postMapper.mapTo(updatedPost),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/{id}")
    public ResponseEntity<PostEntity> deletePost(@PathVariable() Long id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
