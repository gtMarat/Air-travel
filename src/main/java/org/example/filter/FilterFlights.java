package org.example.filter;


import org.example.Flight;

import java.util.List;

@FunctionalInterface
public interface FilterFlights{
    List<Flight> filter(List<Flight> flights);
}
