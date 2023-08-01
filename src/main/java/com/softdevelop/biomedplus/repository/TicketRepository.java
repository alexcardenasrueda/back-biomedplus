package com.softdevelop.biomedplus.repository;

import com.softdevelop.biomedplus.model.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, Long> {

  @Override
  Optional<TicketEntity> findById(Long aLong);
}
