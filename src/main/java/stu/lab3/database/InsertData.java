package stu.lab3.database;

import org.hibernate.Session;

import stu.lab3.Runner;
import stu.lab3.model.Airport;
import stu.lab3.model.City;
import stu.lab3.model.Crew;
import stu.lab3.model.Flight;
import stu.lab3.util.HibernateUtil;
import stu.lab3.util.Menu;

public class InsertData {

    // "Main menu"->"Add data"
    public static void addMenu() {
        new Menu("Select table to add data:")
                .addOption("Airport", InsertData::insertAirport)
                .addOption("City", InsertData::insertCity)
                .addOption("Crew", InsertData::insertCrew)
                .addOption("Flight", InsertData::insertFlight)
                .addOption("Back", Runner::mainMenu)
                .addExit(Runner::shutdown).takeOneInput();
    }

    private static void insertAirport() {
        new Airport().create();
        Runner.returnMenu();
    }

    public static void insertCity() {
        new City().create();
        Runner.returnMenu();
    }

    private static void insertCrew() {
        Crew crew = new Crew().create();
        crew.selectFlight();
        mergeObject(crew);
        System.out.println("Successfully inserted crew " + crew);
        Runner.returnMenu();
    }

    private static void insertFlight() {
        Flight flight = new Flight().create();
        flight.selectCrews();
        mergeObject(flight);
        System.out.println("Successfully inserted flight " + flight);
        Runner.returnMenu();
    }

    public static void insertObject(Object obj) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start transaction
            session.beginTransaction();
            // Save the Model object
            session.persist(obj);
            // commit transaction
            session.getTransaction().commit();
        }
    }

    private static void mergeObject(Object obj) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start transaction
            session.beginTransaction();
            // Save the Model object
            session.merge(obj);
            // commit transaction
            session.getTransaction().commit();
        }
    }

}
