package com.sample.rest.server.core.services;

import com.sample.rest.server.controllers.errorHandler.exceptions.NotFoundException;
import com.sample.rest.server.controllers.resources.book.PurchaseOrderResource;
import com.sample.rest.server.controllers.resources.book.PurchaseResource;
import com.sample.rest.server.controllers.resources.book.PurchaseStatus;
import com.sample.rest.server.core.domain.*;
import com.sample.rest.server.core.services.validation.BookingValidator;
import com.sample.rest.server.repositories.AvailabilityRepository;
import com.sample.rest.server.repositories.BookingRepository;

import javax.inject.Inject;

import static com.sample.rest.server.controllers.resources.book.PurchaseStatus.*;

public class BookingService {

    private final BookingRepository repository;
    private final PriceService priceService;
    private final AvailabilityRepository availabilityRepository;
    private final BookingValidator validator;

    @Inject
    public BookingService(final BookingRepository repository,
                          final PriceService priceService,
                          final AvailabilityRepository availabilityRepository,
                          final BookingValidator validator) {
        this.priceService = priceService;
        this.availabilityRepository = availabilityRepository;
        this.repository = repository;
        this.validator = validator;
    }

    public PurchaseOrderResource book(final PurchaseResource purchase) {
        validator.validate(purchase);

        final Booking booking = new Booking(purchase, getPricesCalculatedForTravelers(purchase));
        final Availability availability = getAvailability(purchase.getCode());

        if (availability.getTickets() < purchase.getTravelers()) {
            return getPurchaseOrderResource(SOLD_OUT, booking);
        }
        return save(booking, availability);
    }

    private PurchaseOrderResource save(final Booking booking, final Availability availability) {
        try {
            availabilityRepository.update(recalculateAvailability(availability, booking.getTravelers()));
            repository.create(booking);
            return getPurchaseOrderResource(SUCCESS, booking);
        } catch (final RuntimeException e) {
            return getPurchaseOrderResource(FAILURE, booking);
        }
    }

    private Availability recalculateAvailability(final Availability availability, final Integer travelers) {
        return new Availability(availability.getCode(), availability.getTickets() - travelers);
    }

    private Price getPricesCalculatedForTravelers(final PurchaseResource resource) {
        return priceService.getPrices(getPriceSearch(resource)).stream().findFirst().orElseThrow(NotFoundException::new);
    }

    private PriceSearch getPriceSearch(final PurchaseResource resource) {
        return new PriceSearch(new Experience(resource.getCode()), resource.getDate(), resource.getTravelers());
    }

    private Availability getAvailability(final Long code) {
        return availabilityRepository.findTickets(code).orElseThrow(NotFoundException::new);
    }

    private PurchaseOrderResource getPurchaseOrderResource(final PurchaseStatus status, final Booking order) {
        return new PurchaseOrderResource(status, order);
    }
}