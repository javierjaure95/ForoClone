package com.foroclone.foroclone.controllers;

import com.foroclone.foroclone.domain.dto.UserCreateDto;
import com.foroclone.foroclone.domain.dto.UserDto;
import com.foroclone.foroclone.domain.entities.UserEntity;
import com.foroclone.foroclone.mappers.Mapper;
import com.foroclone.foroclone.services.UserService;
import com.foroclone.foroclone.services.UserCreateService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    
    private UserService userService;
    private UserCreateService userCreateService;

    private Mapper<UserEntity, UserDto> userMapper;

    private Mapper<UserEntity, UserCreateDto> userCreateMapper;

    public UserController(UserService userService, UserCreateService userCreateService,Mapper<UserEntity, UserDto> userMapper, Mapper<UserEntity, UserCreateDto> userCreateMapper) {
        this.userService = userService;
        this.userCreateService = userCreateService;
        this.userMapper = userMapper;
        this.userCreateMapper = userCreateMapper;

    }

    @PostMapping(path = "/users")
    public ResponseEntity<UserCreateDto> createUser(@RequestBody UserCreateDto user) {
        UserEntity userEntity = userCreateMapper.mapFrom(user);
        UserEntity savedUserEntity = userCreateService.save(userEntity);
        return new ResponseEntity<>(userCreateMapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/users")
    public List<UserDto> listUsers() {
        List<UserEntity> users = userService.findAll();
        return users.stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable() Long id) {
        Optional<UserEntity> foundUser = userService.findOne(id);
        return foundUser.map(userEntity -> {
            UserDto userDto = userMapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> fullUpdateUser(
            @PathVariable() Long id,
            @RequestBody UserCreateDto userCreateDto) {

        if(!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userCreateDto.setId(id);
        UserEntity userEntity = userCreateMapper.mapFrom(userCreateDto);
        UserEntity savedUserEntity = userCreateService.save(userEntity);
        return new ResponseEntity<>(
                userMapper.mapTo(savedUserEntity),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> partialUpdate(
            @PathVariable() Long id,
            @RequestBody UserCreateDto userCreateDto
    ) {
        if(!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = userCreateMapper.mapFrom(userCreateDto);
        UserEntity updatedUser = userService.partialUpdate(id, userEntity);
        return new ResponseEntity<>(
                userMapper.mapTo(updatedUser),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable() Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
