package com.foroclone.foroclone.mappers.impl;

import com.foroclone.foroclone.domain.dto.PostDto;
import com.foroclone.foroclone.domain.entities.PostEntity;
import com.foroclone.foroclone.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component

public class PostMapperImpl implements Mapper<PostEntity, PostDto> {
    
    private ModelMapper modelMapper;

    public PostMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto mapTo(PostEntity communityEntity) {
        return modelMapper.map(communityEntity, PostDto.class);
    }

    @Override
    public PostEntity mapFrom(PostDto communityDto) {
        return modelMapper.map(communityDto, PostEntity.class);
    }

}


