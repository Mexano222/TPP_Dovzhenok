package stu.lab4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import jakarta.transaction.Transactional;
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
@Transactional
public class InsertController {

    @Autowired
    AirportRepository airportRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CrewRepository crewRepository;
    @Autowired
    FlightRepository flightRepository;

    // "Main menu"->"Add data"
    public void showMenu() {
        int sel = new Menu("Select table to add data:")
                .addOption("Airport")
                .addOption("City")
                .addOption("Crew")
                .addOption("Flight")
                .addOption("Exit")
                .takeOneInput();
        switch (sel) {
            case 0 ->
                insertAirport();
            case 1 ->
                insertCity();
            case 2 ->
                insertCrew();
            case 3 ->
                insertFlight();
            case 4 ->
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

    private void insertAirport() {
        Airport airport = new Airport();
        airport = fillAirport(airport);
        airportRepository.save(airport);
        System.out.println("Successfully created airport " + airport.toString());
        returnMenu();

    }

    public void insertCity() {
        City city = new City();
        city = fillCity(city);
        cityRepository.save(city);
        System.out.println("Successfully created city " + city.toString());
        returnMenu();
    }

    private void insertCrew() {
        Crew crew = new Crew();
        crew = fillCrew(crew);
        System.out.println("Successfully created crew " + crew.toString());
        crewRepository.save(crew);

        List<Flight> allFlights = flightRepository.findAll();
        List<String> options = new ArrayList<>();
        allFlights.forEach(f -> options.add(f.getNumber()));

        Set<Integer> selected = new Menu("Select flights from list:")
                .setOptions(options).takeSelectInput();

        for (Integer i : selected) {
            crew.getFlights().add(allFlights.get(i));
        }

        System.out.println("Successfully selected flights for " + crew.toString());

        crewRepository.save(crew);
        System.out.println("Successfully inserted crew " + crew);
        returnMenu();
    }

    private void insertFlight() {
        Flight flight = new Flight();
        flight = fillFligth(flight);
        System.out.println("Successfully created flight " + flight.toString());
        flightRepository.save(flight);

        List<Crew> allCrews = crewRepository.findAll();
        List<String> options = new ArrayList<>();
        allCrews.forEach(c -> options.add(c.getName()));

        Set<Integer> selected = new Menu("Select crews from list:")
                .setOptions(options).takeSelectInput();

        for (Integer i : selected) {
            flight.getCrews().add(allCrews.get(i));
        }

        System.out.println("Successfully selected crews for " + flight.toString());

        flightRepository.save(flight);
        System.out.println("Successfully inserted flight " + flight);
        returnMenu();
    }

    private Airport fillAirport(Airport airport) {
        airport.setName(new Menu("Enter airport name:").takeSimpleInput());
        airport.setCode(new Menu("Enter airport IATA code:").takeSimpleInput());

        // Get list of all cities
        List<City> cities = cityRepository.findAll();
        List<String> options = new ArrayList<>();
        cities.forEach(c -> options.add(c.getName()));

        int selected = new Menu("Select city from list:").setOptions(options).takeOneInput();
        airport.setCity(cities.get(selected));

        return airport;
    }

    private City fillCity(City city) {
        city.setName(new Menu("Enter city name:").takeSimpleInput());
        return city;
    }

    private Crew fillCrew(Crew crew) {
        crew.setName(new Menu("Enter person name:").takeSimpleInput());
        crew.setJob(new Menu("Enter person job:").takeSimpleInput());
        return crew;
    }

    private Flight fillFligth(Flight flight) {
        flight.setNumber(new Menu("Enter flight number:").takeSimpleInput());

        // Get list of all airports
        List<Airport> airports = airportRepository.findAll();

        Menu airportSelect = new Menu();
        airports.forEach(a -> airportSelect.addOption(a.getName()));

        flight.setFromAirport(airports.get(
                airportSelect.setTitle("Select departure airport from list:").takeOneInput()));

        flight.setToAirport(airports.get(
                airportSelect.setTitle("Select arrival airport from list:").takeOneInput()));

        return flight;
    }

}
