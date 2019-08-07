package com.sample.rest.server.repositories.dao;

import com.sample.rest.server.core.domain.Experience;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface ExperienceDAO {

    @SqlQuery("SELECT e.id FROM experiences e " +
            "INNER JOIN availability a ON a.experience_id = e.id " +
            "WHERE e.location = ? " +
            "AND a.tickets >= ?")
    @RegisterConstructorMapper(Experience.class)
    List<Experience> findByNameAndTravelersNumber(final String name, final Integer travelers);

    @SqlQuery("SELECT DISTINCT(location) FROM experiences")
    List<String> findAll();
}
