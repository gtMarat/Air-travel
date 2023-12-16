package org.example.filter;


import org.example.Flight;
import org.example.Segment;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class FilterHoursOnLand implements FilterFlights {

    private final int hoursOnLand;

    public FilterHoursOnLand(int hoursOnLand) {
        this.hoursOnLand = hoursOnLand;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            long minutesOnLand = 0;
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                minutesOnLand += ChronoUnit.MINUTES
                        .between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
            }
            if (minutesOnLand <= hoursOnLand * 60L) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

}
