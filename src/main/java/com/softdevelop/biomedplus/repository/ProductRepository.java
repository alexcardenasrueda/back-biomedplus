package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.EquipoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<EquipoEntity, Long> {

  @Override
  Optional<EquipoEntity> findById(Long aLong);

  @Override
  <S extends EquipoEntity> S save(S entity);
}
