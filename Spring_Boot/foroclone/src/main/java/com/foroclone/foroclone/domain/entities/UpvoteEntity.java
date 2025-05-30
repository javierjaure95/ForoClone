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
@Table(name = "upvotes")
public class UpvoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "upvotes_id_seq")
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity creator;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "comments_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CommentEntity comment;

}