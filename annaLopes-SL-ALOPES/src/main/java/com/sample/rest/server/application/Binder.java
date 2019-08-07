package com.sample.rest.server.application;

import com.sample.rest.server.core.services.BookingService;
import com.sample.rest.server.core.services.PriceService;
import com.sample.rest.server.core.services.SearchService;
import com.sample.rest.server.core.services.validation.BookingValidator;
import com.sample.rest.server.core.services.validation.SearchValidator;
import com.sample.rest.server.core.utils.PriceCalculatorHelper;
import com.sample.rest.server.core.utils.TimeProvider;
import com.sample.rest.server.core.utils.ValidatorHelper;
import com.sample.rest.server.repositories.AvailabilityRepository;
import com.sample.rest.server.repositories.BookingRepository;
import com.sample.rest.server.repositories.ExperienceRepository;
import com.sample.rest.server.repositories.PriceRepository;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import javax.ws.rs.ext.Provider;

@Provider
public class Binder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(TimeProvider.class).to(TimeProvider.class);
        bind(ValidatorHelper.class).to(ValidatorHelper.class).in(Singleton.class);
        bind(PriceCalculatorHelper.class).to(PriceCalculatorHelper.class).in(Singleton.class);
        bind(SearchValidator.class).to(SearchValidator.class).in(Singleton.class);
        bind(BookingValidator.class).to(BookingValidator.class).in(Singleton.class);
        bind(AvailabilityRepository.class).to(AvailabilityRepository.class).in(Singleton.class);
        bind(ExperienceRepository.class).to(ExperienceRepository.class).in(Singleton.class);
        bind(PriceRepository.class).to(PriceRepository.class).in(Singleton.class);
        bind(BookingRepository.class).to(BookingRepository.class).in(Singleton.class);
        bind(BookingService.class).to(BookingService.class).in(Singleton.class);
        bind(PriceService.class).to(PriceService.class).in(Singleton.class);
        bind(SearchService.class).to(SearchService.class).in(Singleton.class);
    }
}
