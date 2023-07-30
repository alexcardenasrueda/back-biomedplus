package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.MaintenanceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRepository extends CrudRepository<MaintenanceEntity, Long> {

    List<MaintenanceEntity> findByEstimatedDateLessThanEqualAndDoneDateIsNull(LocalDate startDate);
}
