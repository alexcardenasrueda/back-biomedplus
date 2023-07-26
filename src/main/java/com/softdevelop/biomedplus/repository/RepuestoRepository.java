package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.EquipoEntity;
import com.softdevelop.biomedplus.model.entity.RepuestoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepuestoRepository extends CrudRepository<RepuestoEntity, Long> {


}
