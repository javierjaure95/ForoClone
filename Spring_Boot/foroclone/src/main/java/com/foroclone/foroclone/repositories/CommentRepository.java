package com.foroclone.foroclone.repositories;

import com.foroclone.foroclone.domain.entities.CommentEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CommentRepository  extends CrudRepository<CommentEntity, Long>{

    List<CommentEntity> findByCreatorId(Long userId);

    List<CommentEntity> findByPostId(Long postId);

    List<CommentEntity> findByParentId(Long parentId);

}
