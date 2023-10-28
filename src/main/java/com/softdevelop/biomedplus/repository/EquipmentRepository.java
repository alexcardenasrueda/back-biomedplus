package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.EquipmentEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipmentRepository extends CrudRepository<EquipmentEntity, Long> {

  @Override
  Optional<EquipmentEntity> findById(Long aLong);

  List<EquipmentEntity> findAllByOrderByNameAsc();
  @Query(value = "SELECT e FROM EquipmentEntity e"
          + " INNER JOIN MaintenanceEntity m ON m.equipment.id = e.id"
          + " WHERE m.doneDate IS NULL ")
  List<EquipmentEntity> findAllByOrderByNameAscWithMaintenance();
}
