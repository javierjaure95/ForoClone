package com.foroclone.foroclone.repositories;

import com.foroclone.foroclone.domain.entities.CommunityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CommunityRepository  extends CrudRepository<CommunityEntity, Long>{

    List<CommunityEntity> findByCreatorId(Long userId);

}
