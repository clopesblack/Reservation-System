package com.sample.rest.server.repositories;

import com.sample.rest.server.core.domain.Booking;
import com.sample.rest.server.repositories.dao.BookingDAO;

public class BookingRepository extends BaseRepository {

    public void create(final Booking booking) {
        getDataSource().useExtension(BookingDAO.class, dao -> dao.create(booking));
    }
}
