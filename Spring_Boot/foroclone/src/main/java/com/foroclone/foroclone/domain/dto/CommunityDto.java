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
public class CommunityDto {
    
    private Long id;

    private String name;

    private String description;

    private String title;

    private LocalDateTime createdAt;

    private UserDto creator;

}