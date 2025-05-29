package com.foroclone.foroclone.mappers.impl;

import com.foroclone.foroclone.domain.dto.UserCreateDto;
import com.foroclone.foroclone.domain.entities.UserEntity;
import com.foroclone.foroclone.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserCreateMapperImpl implements Mapper<UserEntity, UserCreateDto> {

    private ModelMapper modelMapper;

    public UserCreateMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity mapFrom(UserCreateDto userCreateDto) {
        return modelMapper.map(userCreateDto, UserEntity.class);
    }

    @Override
    public UserCreateDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserCreateDto.class);
    }
}
