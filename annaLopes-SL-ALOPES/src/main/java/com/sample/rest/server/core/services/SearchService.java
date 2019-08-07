package com.sample.rest.server.core.services;

import com.sample.rest.server.controllers.resources.search.ExperienceResource;
import com.sample.rest.server.controllers.resources.search.ItemResource;
import com.sample.rest.server.controllers.resources.search.ResultResource;
import com.sample.rest.server.controllers.resources.search.SearchResource;
import com.sample.rest.server.core.domain.Experience;
import com.sample.rest.server.core.domain.PriceSearch;
import com.sample.rest.server.core.services.validation.SearchValidator;
import com.sample.rest.server.repositories.ExperienceRepository;

import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SearchService {

    private final PriceService priceService;
    private final SearchValidator validator;
    private final ExperienceRepository experienceRepository;

    @Inject
    public SearchService(final PriceService priceService,
                         final SearchValidator searchValidator,
                         final ExperienceRepository experienceRepository) {
        this.priceService = priceService;
        this.validator = searchValidator;
        this.experienceRepository = experienceRepository;
    }

    public ExperienceResource getExperiences(final SearchResource search) {
        validator.validate(search);
        return new ExperienceResource(search, getResults(search));
    }

    private ResultResource getResults(final SearchResource search) {
        return new ResultResource(getItems(findExperiencesByNameAndTravelersNumber(search), search));
    }

    private List<ItemResource> getItems(final List<Experience> experiences, final SearchResource search) {
        return priceService.getPrices(new PriceSearch(experiences, search.getDate(), search.getTravelers()))
                .stream().map(ItemResource::new).collect(toList());
    }

    private List<Experience> findExperiencesByNameAndTravelersNumber(final SearchResource search) {
        return experienceRepository.findByNameAndTravelersNumber(search.getLocation(), search.getTravelers());
    }
}