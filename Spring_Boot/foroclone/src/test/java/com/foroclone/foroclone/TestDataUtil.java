package com.foroclone.foroclone;

import com.foroclone.foroclone.domain.entities.UserEntity;

public class TestDataUtil {
    private TestDataUtil(){
    }

    public static UserEntity createTestUserEntityA() {
        return UserEntity.builder()
                .id(1L)
                .username("Abigail Rose")
                .email("abigail@gmail.com")
                .password("1234566")
                .build();
    }
}
