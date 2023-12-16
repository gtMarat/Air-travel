package com.gridnine.testing.filter;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class FilterDepartureNotBeforeAppointedTimeTest {

    @Test
    void filter() {
        //given
        LocalDateTime ldtNow = LocalDateTime.now();

        Flight flightWithDepartureBeforeAppointed = new Flight(Arrays.asList(
                new Segment(ldtNow.minusDays(2), ldtNow.plusHours(2))
                , new Segment(ldtNow.plusHours(6), ldtNow.plusHours(7))));
        Flight flightWithoutDepartureBeforeAppointed = new Flight(Arrays.asList(
                new Segment(ldtNow, ldtNow.plusHours(2))
                , new Segment(ldtNow.plusHours(3), ldtNow.plusHours(7))));

        List<Flight> flights = Arrays.asList(flightWithDepartureBeforeAppointed, flightWithoutDepartureBeforeAppointed);


        //then
        FilterFlights filterFlights = new FilterDepartureNotBeforeAppointedTime(ldtNow);
        List<Flight> flightsResult = filterFlights.filter(flights);
        List<Flight> flightsExpected = List.of(flightWithoutDepartureBeforeAppointed);
        //when
        Assertions.assertEquals(flightsExpected, flightsResult);
    }
}