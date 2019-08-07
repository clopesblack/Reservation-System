package com.sample.rest.server.repositories;

import com.sample.rest.server.core.domain.Experience;
import com.sample.rest.server.core.domain.Price;
import com.sample.rest.server.repositories.dao.PriceDAO;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PriceRepository extends BaseRepository {

    public List<Price> getPricesByExperiences(final List<Experience> experiences) {
        return getDataSource().withExtension(PriceDAO.class,
                dao -> dao.findPricesByExperiences(experiences.stream().map(Experience::getCode).collect(toList())));
    }
}