package com.softdevelop.biomedplus.util;

public class JpaConstants {

  public static final String FIND_ALL_ACTIVE_TICKETS = "SELECT t FROM TicketEntity t"
      + " INNER JOIN StatusEntity s ON t.status = s.id"
      + " WHERE s.name = 'CREATED' OR s.name = 'IN_PROCESS'";

}
