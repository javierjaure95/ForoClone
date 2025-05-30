package com.foroclone.foroclone.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpvoteDto {
    
    private Long id;

    private LocalDateTime createdAt;

    private Long creator;

    private Long post;

    private Long comment;

}