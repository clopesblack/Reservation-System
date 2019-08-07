package com.sample.rest.server.repositories;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.sample.rest.server.MatcherAssert.endsWith;
import static com.sample.rest.server.MatcherAssert.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class LocationsRepositoryTest {

    private ExperienceRepository locationsRepository;

    @Before
    public void setup() {
        locationsRepository = new ExperienceRepository();
    }

    @Test
    public void listLocations() {
        List<String> locations = locationsRepository.findAll();

        assertThat(locations, hasItems("ROME", "PARIS", "LONDON", "MADRID", "BERLIN", "BARCELONA", "PORTO", "LISBON", "STOCKHOLM", "AMSTERDAM"));
        assertThat(locations.size(), is(equalTo(10)));
        startsWith(locations, "ROME");
        endsWith(locations, "AMSTERDAM");
    }

}
