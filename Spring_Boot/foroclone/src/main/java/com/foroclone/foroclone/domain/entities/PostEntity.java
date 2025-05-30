package com.foroclone.foroclone.domain.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_id_seq")
    private Long id;

    @Column(nullable = false)
    private String title;

    private String content;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "communities_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CommunityEntity community;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity creator;
}