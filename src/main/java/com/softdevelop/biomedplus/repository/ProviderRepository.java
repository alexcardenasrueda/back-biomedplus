package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.ProviderEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends CrudRepository<ProviderEntity, Long> {

  List<ProviderEntity> findAllByOrderByNameAsc();
}
