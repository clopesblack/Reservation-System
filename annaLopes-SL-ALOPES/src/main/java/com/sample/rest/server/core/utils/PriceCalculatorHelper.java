package com.sample.rest.server.core.utils;

import com.sample.rest.server.core.domain.Price;
import com.sample.rest.server.core.domain.PriceSearch;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_EVEN;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.now;
import static java.time.LocalDate.parse;
import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toList;

public class PriceCalculatorHelper {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final BigDecimal MORE_THAN_30_DAYS = valueOf(0.8);
    private static final BigDecimal BETWEEN_30_AND_16_DAYS = valueOf(1);
    private static final BigDecimal BETWEEN_15_AND_3_DAYS = valueOf(1.2);
    private static final BigDecimal LESS_THAN_3_DAYS = valueOf(1.5);

    public List<Price> calculateFinalPrices(final List<Price> prices, final PriceSearch search) {
        return prices.stream().map(price -> calculateFinalPrice(price, search)).collect(toList());
    }

    private Price calculateFinalPrice(final Price price, final PriceSearch search) {
        final BigDecimal ratedPrice = price.getPrice().multiply(getBasePrice(search.getDate())).multiply(valueOf(search.getTravelers())).setScale(2, ROUND_HALF_EVEN);
        return new Price(price.getCode(), ratedPrice, price.getCurrency());
    }

    private BigDecimal getBasePrice(final String dateSearched) {
        long distance = DAYS.between(now(UTC), parse(dateSearched, DATE_TIME_FORMATTER));
        if (distance > 30) {
            return MORE_THAN_30_DAYS;
        }

        if (distance > 15) {
            return BETWEEN_30_AND_16_DAYS;
        }

        if (distance > 3) {
            return BETWEEN_15_AND_3_DAYS;
        }

        return LESS_THAN_3_DAYS;
    }
}