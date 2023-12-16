package com.gridnine.testing.filter;


import com.gridnine.testing.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FilterDepartureNotBeforeAppointedTime implements FilterFlights {

    private final LocalDateTime appointedTime;

    public FilterDepartureNotBeforeAppointedTime(LocalDateTime appointedTime) {
        this.appointedTime = appointedTime;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .limit(1)
                        .anyMatch(segment -> !segment.getDepartureDate().isBefore(appointedTime)))
                .collect(Collectors.toList());
    }

}
