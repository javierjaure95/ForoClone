package com.foroclone.foroclone.mappers.impl;

import com.foroclone.foroclone.domain.dto.CommentDto;
import com.foroclone.foroclone.domain.entities.CommentEntity;
import com.foroclone.foroclone.domain.entities.PostEntity;
import com.foroclone.foroclone.domain.entities.UserEntity;
import com.foroclone.foroclone.mappers.Mapper;
import org.springframework.stereotype.Component;
@Component

public class CommentMapperImpl implements Mapper<CommentEntity, CommentDto> {
    

    @Override
    public CommentDto mapTo(CommentEntity commentEntity) {
        return CommentDto.builder()
        .id(commentEntity.getId())
        .content(commentEntity.getContent())
        .creator(commentEntity.getCreator().getId())
        .post(commentEntity.getPost().getId())
        .parent(commentEntity.getParent() != null ? commentEntity.getParent().getId() : null)
        .createdAt(commentEntity.getCreatedAt())
        .build();
    }

    @Override
    public CommentEntity mapFrom(CommentDto commentDto) {
        UserEntity creator = new UserEntity();
        creator.setId(commentDto.getCreator());

        PostEntity post = new PostEntity();
        post.setId(commentDto.getPost());

        CommentEntity parent = null;
        if (commentDto.getParent() != null) {
            parent = new CommentEntity();
            parent.setId(commentDto.getParent());
        }

        return CommentEntity.builder()
            .id(commentDto.getId())
            .content(commentDto.getContent())
            .createdAt(commentDto.getCreatedAt())
            .creator(creator)
            .post(post)
            .parent(parent)
            .build();
    }

}


