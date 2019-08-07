package com.sample.rest.server.core.utils;

import com.sample.rest.server.core.domain.Experience;
import com.sample.rest.server.core.domain.Price;
import com.sample.rest.server.core.domain.PriceSearch;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_EVEN;
import static java.time.LocalDate.now;
import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class PriceCalculatorHelperTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = ofPattern("yyyyMMdd");
    private static PriceCalculatorHelper priceCalculatorHelper;

    @BeforeClass
    public static void setup() {
        priceCalculatorHelper = new PriceCalculatorHelper();
    }

    @Test
    public void calculateFinalPricesShouldReturnCorrectPricesForFutureTravelDateBiggerThan30Days() {
        final List<Price> prices = Collections.singletonList(new Price(1L, new BigDecimal(100), "EUR"));
        final PriceSearch priceSearch = new PriceSearch(new Experience(1L), now(UTC).plusDays(31).format(DATE_TIME_FORMATTER), 1);

        final List<Price> calculatedPrices = priceCalculatorHelper.calculateFinalPrices(prices, priceSearch);
        assertThat(calculatedPrices.get(0).getPrice(), is(equalTo(new BigDecimal(80.0).setScale(2, ROUND_HALF_EVEN))));
    }

    @Test
    public void calculateFinalPricesShouldReturnCorrectPricesForFutureTravelDateBetween16And30Days() {
        final List<Price> prices = Collections.singletonList(new Price(1L, new BigDecimal(100), "EUR"));
        final PriceSearch priceSearch = new PriceSearch(new Experience(1L), now(UTC).plusDays(20).format(DATE_TIME_FORMATTER), 1);

        final List<Price> calculatedPrices = priceCalculatorHelper.calculateFinalPrices(prices, priceSearch);
        assertThat(calculatedPrices.get(0).getPrice(), is(equalTo(new BigDecimal(100.0).setScale(2, ROUND_HALF_EVEN))));
    }

    @Test
    public void calculateFinalPricesShouldReturnCorrectPricesForFutureTravelDateBetween15And3Days() {
        final List<Price> prices = Collections.singletonList(new Price(1L, new BigDecimal(100), "EUR"));
        final PriceSearch priceSearch = new PriceSearch(new Experience(1L), now(UTC).plusDays(10).format(DATE_TIME_FORMATTER), 1);

        final List<Price> calculatedPrices = priceCalculatorHelper.calculateFinalPrices(prices, priceSearch);
        assertThat(calculatedPrices.get(0).getPrice(), is(equalTo(new BigDecimal(120.0).setScale(2, ROUND_HALF_EVEN))));
    }

    @Test
    public void calculateFinalPricesShouldReturnCorrectPricesForFutureTravelDateBetween0And2Days() {
        final List<Price> prices = Collections.singletonList(new Price(1L, new BigDecimal(100), "EUR"));
        final PriceSearch priceSearch = new PriceSearch(new Experience(1L), now(UTC).plusDays(1).format(DATE_TIME_FORMATTER), 1);

        final List<Price> calculatedPrices = priceCalculatorHelper.calculateFinalPrices(prices, priceSearch);
        assertThat(calculatedPrices.get(0).getPrice(), is(equalTo(new BigDecimal(150.0).setScale(2, ROUND_HALF_EVEN))));
    }


}
