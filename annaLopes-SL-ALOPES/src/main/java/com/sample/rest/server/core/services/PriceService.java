package com.sample.rest.server.core.services;

import com.sample.rest.server.core.domain.Experience;
import com.sample.rest.server.core.domain.Price;
import com.sample.rest.server.core.domain.PriceSearch;
import com.sample.rest.server.core.utils.PriceCalculatorHelper;
import com.sample.rest.server.repositories.PriceRepository;

import javax.inject.Inject;
import java.util.List;

import static java.util.Collections.emptyList;

public class PriceService {

    private final PriceRepository repository;
    private final PriceCalculatorHelper helper;

    @Inject
    public PriceService(final PriceRepository repository, final PriceCalculatorHelper helper) {
        this.repository = repository;
        this.helper = helper;
    }

    public List<Price> getPrices(final PriceSearch search) {
        if(search.getExperiences().isEmpty()){
            return emptyList();
        }
        return helper.calculateFinalPrices(getPricesByExperiences(search.getExperiences()), search);
    }

    private List<Price> getPricesByExperiences(final List<Experience> experiences) {
        return repository.getPricesByExperiences(experiences);
    }
}