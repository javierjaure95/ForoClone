package com.foroclone.foroclone.repositories;

import com.foroclone.foroclone.domain.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PostRepository  extends CrudRepository<PostEntity, Long>{

    List<PostEntity> findByCreatorId(Long userId);

    List<PostEntity> findByCommunityId(Long communityId);

}
