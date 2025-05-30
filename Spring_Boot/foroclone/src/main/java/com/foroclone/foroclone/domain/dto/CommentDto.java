package com.foroclone.foroclone.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    private Long creator;

    private Long post;

    private Long parent;

    private List<CommentDto> replies;

}