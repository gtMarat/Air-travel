package org.example.filter;

import org.example.Flight;
import org.example.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class FilterHoursOnLandTest {


    @Test
    void filter() {
        //given
        LocalDateTime ldtNow = LocalDateTime.now();
        int hoursOnLand = 3;
        Flight flightWithThreeHoursOnLand = new Flight(Arrays.asList(
                new Segment(ldtNow, ldtNow.plusHours(2))
                , new Segment(ldtNow.plusHours(6), ldtNow.plusHours(7))));
        Flight flightWithoutThreeHoursOnLand = new Flight(Arrays.asList(
                new Segment(ldtNow, ldtNow.plusHours(2))
                , new Segment(ldtNow.plusHours(3), ldtNow.plusHours(7))));

        List<Flight> flights = Arrays.asList(flightWithThreeHoursOnLand, flightWithoutThreeHoursOnLand);

        //then
        FilterFlights filterFlights = new FilterHoursOnLand(hoursOnLand);
        List<Flight> flightsResult = filterFlights.filter(flights);

        List<Flight> flightsExpected = List.of(flightWithoutThreeHoursOnLand);
        //when
        Assertions.assertEquals(flightsExpected, flightsResult);
    }
}