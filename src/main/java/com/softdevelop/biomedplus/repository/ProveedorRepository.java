package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.EquipoEntity;
import com.softdevelop.biomedplus.model.entity.ProveedorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository  extends CrudRepository<ProveedorEntity, Long> {
}
