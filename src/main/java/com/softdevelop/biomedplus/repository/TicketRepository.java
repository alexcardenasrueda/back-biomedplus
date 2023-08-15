package com.softdevelop.biomedplus.repository;


import static com.softdevelop.biomedplus.util.JpaConstants.FIND_ALL_ACTIVE_TICKETS;

import com.softdevelop.biomedplus.model.entity.TicketEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<TicketEntity, Long> {

  @Override
  Optional<TicketEntity> findById(Long aLong);

  @Query(value = FIND_ALL_ACTIVE_TICKETS)
  List<TicketEntity> findAllActiveTickets();
}
