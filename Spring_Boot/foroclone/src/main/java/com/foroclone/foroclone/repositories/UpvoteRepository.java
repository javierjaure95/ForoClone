package com.foroclone.foroclone.repositories;

import com.foroclone.foroclone.domain.entities.UpvoteEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UpvoteRepository  extends CrudRepository<UpvoteEntity, Long>{

    List<UpvoteEntity> findByCreatorId(Long userId);

    List<UpvoteEntity> findByPostId(Long postId);

    List<UpvoteEntity> findByCommentId(Long commentId);

}
