package com.foroclone.foroclone.services;
import com.foroclone.foroclone.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> findAll();

    Optional<UserEntity> findOne(Long id);

    boolean isExists(Long id);

    UserEntity partialUpdate(Long id, UserEntity userEntity);

    void delete(Long id);

    void changePassword(Long userId, String newPassword);
}
