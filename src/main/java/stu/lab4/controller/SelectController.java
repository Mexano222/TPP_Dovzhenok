package stu.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import stu.lab4.model.Airport;
import stu.lab4.model.City;
import stu.lab4.model.Crew;
import stu.lab4.model.Flight;
import stu.lab4.repo.AirportRepository;
import stu.lab4.repo.CityRepository;
import stu.lab4.repo.CrewRepository;
import stu.lab4.repo.FlightRepository;
import stu.lab4.util.Menu;

@Controller
public class SelectController {

    @Autowired
    AirportRepository airportRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CrewRepository crewRepository;
    @Autowired
    FlightRepository flightRepository;

    // "Main menu"->"Show data"
    public void showMenu() {
        int sel = new Menu("Choose what you want to display:")
                .addOption("Show airports")
                .addOption("Show cities")
                .addOption("Show crews")
                .addOption("Show flights")
                .addOption("Show cities with airports")
                .addOption("Show flights with crews")
                .addOption("Show flights with airports")
                .addOption("Exit")
                .takeOneInput();
        switch (sel) {
            case 0 ->
                showAirports();
            case 1 ->
                showCities();
            case 2 ->
                showCrews();
            case 3 ->
                showFlights();
            case 4 ->
                showCitiesAndAirports();
            case 5 ->
                showFlightsAndCrews();
            case 6 ->
                showFlightsAndAirports();
            case 7 ->
                System.exit(0);
        }

    }

    public void returnMenu() {
        int sel = new Menu().addOption("Back to start")
                .addOption("Exit")
                .takeOneInput();
        switch (sel) {
            case 0 ->
                showMenu();
            case 1 ->
                System.exit(0);
        }
    }

    // "Main menu"->"Show data"->"Show airports"
    private void showAirports() {
        airportRepository.findAll().forEach(System.out::println);
        returnMenu();
    }

    // "Main menu"->"Show data"->"Show cities"
    private void showCities() {
        cityRepository.findAll().forEach(System.out::println);
        returnMenu();
    }

    // "Main menu"->"Show data"->"Show crews"
    private void showCrews() {
        crewRepository.findAll().forEach(System.out::println);
        returnMenu();
    }

    // "Main menu"->"Show data"->"Show flights"
    private void showFlights() {
        flightRepository.findAll().forEach(System.out::println);
        returnMenu();
    }

    // "Main menu"->"Show data"->"Show cities and airports"
    private void showCitiesAndAirports() {
        for (City city : cityRepository.findAll()) {
            System.out.println(city.toString());
            for (Airport airport : city.getAirports()) {
                System.out.println(" - " + airport.toString());
            }
        }
        returnMenu();
    }

    // "Main menu"->"Show data"->"Show flights and crews"
    private void showFlightsAndCrews() {
        for (Flight flight : flightRepository.findAll()) {
            System.out.println(flight.toString());
            for (Crew crew : flight.getCrews()) {
                System.out.println("\t" + crew.toString());
            }
        }
        returnMenu();
    }

    // "Main menu"->"Show data"->"Show flights and airports"
    private void showFlightsAndAirports() {
        for (Flight flight : flightRepository.findAll()) {
            System.out.println(flight.toString());
            System.out.println(" <-" + flight.getFromAirport().toString());
            System.out.println(" ->" + flight.getToAirport().toString());
        }
        returnMenu();
    }

}
