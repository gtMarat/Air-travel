package com.gridnine.testing.filter;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class FilterDepartureBeforeArrivalTest {

    @Test
    void filter() {
        //given
        LocalDateTime ldtNow = LocalDateTime.now();

        Flight flightWithArrivalBeforeDeparture = new Flight(Arrays.asList(
                new Segment(ldtNow, ldtNow.minusHours(2))
                , new Segment(ldtNow.plusHours(6), ldtNow.minusHours(7))));
        Flight flightWithoutArrivalBeforeDeparture = new Flight(Arrays.asList(
                new Segment(ldtNow, ldtNow.plusHours(2))
                , new Segment(ldtNow.plusHours(3), ldtNow.plusHours(7))));

        List<Flight> flights = Arrays.asList(flightWithArrivalBeforeDeparture, flightWithoutArrivalBeforeDeparture);


        //then
        FilterFlights filterFlights = new FilterDepartureBeforeArrival();
        List<Flight> flightsResult = filterFlights.filter(flights);
        List<Flight> flightsExpected = List.of(flightWithoutArrivalBeforeDeparture);
        //when
        Assertions.assertEquals(flightsExpected, flightsResult);
    }
}