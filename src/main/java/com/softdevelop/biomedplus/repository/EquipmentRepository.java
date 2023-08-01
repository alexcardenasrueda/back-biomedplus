package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.EquipmentEntity;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends CrudRepository<EquipmentEntity, Long> {

  @Override
  Optional<EquipmentEntity> findById(Long aLong);

}
