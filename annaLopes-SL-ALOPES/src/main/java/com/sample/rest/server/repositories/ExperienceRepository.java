package com.sample.rest.server.repositories;

import com.sample.rest.server.core.domain.Experience;
import com.sample.rest.server.repositories.dao.ExperienceDAO;

import java.util.List;

public class ExperienceRepository extends BaseRepository {

    public List<Experience> findByNameAndTravelersNumber(final String name, final Integer travelers) {
        return getDataSource().withExtension(ExperienceDAO.class,
                dao -> dao.findByNameAndTravelersNumber(name, travelers));
    }

    public List<String> findAll() {
        return getDataSource().withExtension(ExperienceDAO.class, ExperienceDAO::findAll);
    }

}