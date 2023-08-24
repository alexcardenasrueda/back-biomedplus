package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.SpareEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpareRepository extends CrudRepository<SpareEntity, Long> {
    List<SpareEntity> findAll();

  List<SpareEntity> findAllByOrderByNameAsc();
}
