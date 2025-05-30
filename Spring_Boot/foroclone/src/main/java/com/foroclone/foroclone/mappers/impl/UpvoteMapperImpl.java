package com.foroclone.foroclone.mappers.impl;

import com.foroclone.foroclone.domain.dto.UpvoteDto;
import com.foroclone.foroclone.domain.entities.UpvoteEntity;
import com.foroclone.foroclone.domain.entities.PostEntity;
import com.foroclone.foroclone.domain.entities.UserEntity;
import com.foroclone.foroclone.domain.entities.CommentEntity;
import com.foroclone.foroclone.mappers.Mapper;
import org.springframework.stereotype.Component;
@Component

public class UpvoteMapperImpl implements Mapper<UpvoteEntity, UpvoteDto> {
    

    @Override
    public UpvoteDto mapTo(UpvoteEntity upvoteEntity) {
        return UpvoteDto.builder()
        .id(upvoteEntity.getId())
        .creator(upvoteEntity.getCreator().getId())
        .post(upvoteEntity.getPost() != null ? upvoteEntity.getPost().getId() : null)
        .comment(upvoteEntity.getComment() != null ? upvoteEntity.getComment().getId() : null)
        .createdAt(upvoteEntity.getCreatedAt())
        .build();
    }

    @Override
    public UpvoteEntity mapFrom(UpvoteDto upvoteDto) {
        UserEntity creator = new UserEntity();
        creator.setId(upvoteDto.getCreator());

        PostEntity post = null;
        if (upvoteDto.getPost() != null) {
            post = new PostEntity();
            post.setId(upvoteDto.getPost());
        }

        CommentEntity comment = null;
        if (upvoteDto.getComment() != null) {
            comment = new CommentEntity();
            comment.setId(upvoteDto.getComment());
        }

        return UpvoteEntity.builder()
            .id(upvoteDto.getId())
            .createdAt(upvoteDto.getCreatedAt())
            .creator(creator)
            .post(post)
            .comment(comment)
            .build();
    }

}


