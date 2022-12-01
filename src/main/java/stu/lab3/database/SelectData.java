package stu.lab3.database;

import stu.lab3.Runner;
import stu.lab3.model.Airport;
import stu.lab3.model.City;
import stu.lab3.model.Crew;
import stu.lab3.model.Flight;
import stu.lab3.util.Menu;

public class SelectData {

    // "Main menu"->"Show data"
    public static void showMenu() {
        new Menu("Choose what you want to display:")
                .addOption("Show airports", SelectData::showAirports)
                .addOption("Show cities", SelectData::showCities)
                .addOption("Show crews", SelectData::showCrews)
                .addOption("Show flights", SelectData::showFlights)

                .addOption("Show cities with airports", SelectData::showCitiesAndAirports)
                .addOption("Show flights with crews", SelectData::showFlightsAndCrews)
                .addOption("Show flights with airports", SelectData::showFlightsAndAirports)
                // .addOption("Show flights to city", null)
                .addOption("Back", Runner::mainMenu)
                .addExit(Runner::shutdown).takeOneInput();
    }

    // "Main menu"->"Show data"->"Show airports"
    private static void showAirports() {
        Airport.select().forEach(System.out::println);
        Runner.returnMenu();
    }

    // "Main menu"->"Show data"->"Show cities"
    private static void showCities() {
        City.select().forEach(System.out::println);
        Runner.returnMenu();
    }

    // "Main menu"->"Show data"->"Show crews"
    private static void showCrews() {
        Crew.select().forEach(System.out::println);
        Runner.returnMenu();
    }

    // "Main menu"->"Show data"->"Show flights"
    private static void showFlights() {
        Flight.select().forEach(System.out::println);
        Runner.returnMenu();
    }

    // "Main menu"->"Show data"->"Show cities and airports"
    private static void showCitiesAndAirports() {
        for (City city : City.select()) {
            System.out.println(city.toString());
            for (Airport airport : city.getAirports())
                System.out.println(" - " + airport.toString());
        }
        Runner.returnMenu();
    }

    // "Main menu"->"Show data"->"Show flights and crews"
    private static void showFlightsAndCrews() {
        for (Flight flight : Flight.select()) {
            System.out.println(flight.toString());
            for (Crew crew : flight.getCrews())
                System.out.println("\t" + crew.toString());
        }
        Runner.returnMenu();
    }

    // "Main menu"->"Show data"->"Show flights and airports"
    private static void showFlightsAndAirports() {
        for (Flight flight : Flight.select()) {
            System.out.println(flight.toString());
            System.out.println(" <-" + flight.getFromAirport().toString());
            System.out.println(" ->" + flight.getToAirport().toString());
        }
        Runner.returnMenu();
    }

}
