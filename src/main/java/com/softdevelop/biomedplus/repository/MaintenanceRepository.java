package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.ManteinanceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRepository extends CrudRepository<ManteinanceEntity, Long> {

    List<ManteinanceEntity> findByEstimatedDateLessThanEqualAndDoneDateIsNull(LocalDate startDate);
}
