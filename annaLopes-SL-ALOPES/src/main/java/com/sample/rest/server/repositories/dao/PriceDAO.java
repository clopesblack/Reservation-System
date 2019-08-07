package com.sample.rest.server.repositories.dao;

import com.sample.rest.server.core.domain.Price;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PriceDAO {

    @SqlQuery("SELECT * FROM prices WHERE experience_id in (<ids>)")
    @RegisterConstructorMapper(Price.class)
    List<Price> findPricesByExperiences(@BindList("ids") List<Long> ids);
}
