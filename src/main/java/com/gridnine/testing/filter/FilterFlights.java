package com.gridnine.testing.filter;


import com.gridnine.testing.Flight;

import java.util.List;

@FunctionalInterface
public interface FilterFlights{
    List<Flight> filter(List<Flight> flights);
}
