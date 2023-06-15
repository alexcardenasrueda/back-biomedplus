package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.EquipoEntity;
import com.softdevelop.biomedplus.model.entity.MantenimientoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface MantenimientoRepository extends CrudRepository<MantenimientoEntity, Long> {

    List<MantenimientoEntity> findByFechaEstimadaLessThanEqualAndFechaEjecutadaIsNull(LocalDate startDate);
}
