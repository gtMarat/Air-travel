package com.gridnine.testing.filter;


import com.gridnine.testing.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class FilterDepartureBeforeArrival implements FilterFlights {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .noneMatch(segment -> segment.getDepartureDate().isAfter(segment.getArrivalDate())))
                .collect(Collectors.toList());
    }
}