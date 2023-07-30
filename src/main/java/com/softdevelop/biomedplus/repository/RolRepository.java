package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.RolEntity;
import com.softdevelop.biomedplus.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<RolEntity, Long> {
}
