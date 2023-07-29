package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.EquipementEntity;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipementRepository extends CrudRepository<EquipementEntity, Long> {

  @Override
  Optional<EquipementEntity> findById(Long aLong);

  @Override
  <S extends EquipementEntity> S save(S entity);

}
