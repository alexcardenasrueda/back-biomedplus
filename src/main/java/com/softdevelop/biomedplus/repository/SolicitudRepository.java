package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.SolicitudEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitudRepository extends CrudRepository<SolicitudEntity, Long> {

  @Override
  Optional<SolicitudEntity> findById(Long aLong);
}
