package com.foroclone.foroclone.services.impl;

import com.foroclone.foroclone.domain.entities.UserEntity;
import com.foroclone.foroclone.repositories.UserRepository;
import com.foroclone.foroclone.services.UserCreateService;
import com.foroclone.foroclone.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserCreateServiceImpl implements UserCreateService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserCreateServiceImpl(UserRepository userRepository,
                                BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        if (userRepository.existsByUsername(userEntity.getUsername())) {
            throw new UserAlreadyExistsException("Username is already taken");
        }

        if (userRepository.existsByEmail(userEntity.getEmail())) {
            throw new UserAlreadyExistsException("Email is already in use");
        }
        String hashedPassword = passwordEncoder.encode(userEntity.getPassword());

        userEntity.setPassword(hashedPassword);

        return userRepository.save(userEntity);
    }

    
    @Override
    public Long count() {
        return userRepository.count();
    }
}
