package com.sample.rest.server.repositories;

import com.sample.rest.server.core.domain.Availability;
import com.sample.rest.server.repositories.dao.AvailabilityDAO;

import java.util.Optional;

public class AvailabilityRepository extends BaseRepository {

    public Optional<Availability> findTickets(final Long codeExperience) {
        return getDataSource().withExtension(AvailabilityDAO.class, dao -> dao.findTickets(codeExperience));
    }

    public void update(final Availability availability) {
        getDataSource().useExtension(AvailabilityDAO.class, dao -> dao.update(availability));
    }
}
