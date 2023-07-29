package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.TickerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TickerRepository extends CrudRepository<TickerEntity, Long> {

  @Override
  Optional<TickerEntity> findById(Long aLong);
}
