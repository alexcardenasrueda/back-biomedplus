package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.SpareEntity;
import com.softdevelop.biomedplus.model.entity.StatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<StatusEntity, Long> {
}
