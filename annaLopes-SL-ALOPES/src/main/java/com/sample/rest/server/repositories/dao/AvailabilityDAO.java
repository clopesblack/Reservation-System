package com.sample.rest.server.repositories.dao;

import com.sample.rest.server.core.domain.Availability;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;

public interface AvailabilityDAO {

    @SqlQuery("SELECT * FROM availability WHERE experience_id = ? ")
    @RegisterConstructorMapper(Availability.class)
    Optional<Availability> findTickets(final Long code);

    @SqlUpdate("UPDATE availability SET tickets = :a.tickets WHERE experience_id = :a.code")
    void update(@BindBean("a") final Availability availability);
}
