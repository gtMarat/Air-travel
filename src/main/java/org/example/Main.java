package org.example;

import org.example.filter.FilterDepartureBeforeArrival;
import org.example.filter.FilterDepartureNotBeforeAppointedTime;
import org.example.filter.FilterFlights;
import org.example.filter.FilterHoursOnLand;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Исключаются вылеты до текущего момента времени");
        FilterFlights filterDepartureBeforeAppointedTime = new FilterDepartureNotBeforeAppointedTime(LocalDateTime.now());
        filterDepartureBeforeAppointedTime.filter(flights).forEach(System.out::println);

        System.out.println("Исключаются вылеты с сегментами с датой прилёта раньше даты вылета");
        FilterFlights filterArrivalBeforeDeparture = new FilterDepartureBeforeArrival();
        filterArrivalBeforeDeparture.filter(flights).forEach(System.out::println);

        System.out.println("Исключаются вылеты, где общее время, проведённое на земле превышает два часа ");
        FilterFlights filterHoursOnLand = new FilterHoursOnLand(2);
        filterHoursOnLand.filter(flights).forEach(System.out::println);

        //демонстрация возможности расширения функциональности фильтров
        //создание "одноразового" фильтра (фильтрует полеты из одного сегмента)
        FilterFlights filterFlights = flightsTest ->
                flightsTest.stream()
                        .filter(flight -> flight.getSegments().size() == 1)
                        .collect(Collectors.toList());

    }
}