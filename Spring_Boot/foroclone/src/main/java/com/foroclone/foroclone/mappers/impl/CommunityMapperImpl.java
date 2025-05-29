package com.foroclone.foroclone.mappers.impl;

import com.foroclone.foroclone.domain.dto.CommunityDto;
import com.foroclone.foroclone.domain.entities.CommunityEntity;
import com.foroclone.foroclone.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component

public class CommunityMapperImpl implements Mapper<CommunityEntity, CommunityDto> {
    
    private ModelMapper modelMapper;

    public CommunityMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CommunityDto mapTo(CommunityEntity communityEntity) {
        return modelMapper.map(communityEntity, CommunityDto.class);
    }

    @Override
    public CommunityEntity mapFrom(CommunityDto communityDto) {
        return modelMapper.map(communityDto, CommunityEntity.class);
    }

}


