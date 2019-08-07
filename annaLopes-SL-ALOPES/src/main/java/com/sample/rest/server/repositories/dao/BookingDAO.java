package com.sample.rest.server.repositories.dao;

import com.sample.rest.server.core.domain.Booking;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface BookingDAO {

    @SqlUpdate("INSERT INTO bookings(experience_id, booking_date, travelers, price, currency) " +
            "VALUES (:order.code, :order.date, :order.travelers, :order.price, :order.currency)")
    void create(@BindBean("order") Booking booking);
}