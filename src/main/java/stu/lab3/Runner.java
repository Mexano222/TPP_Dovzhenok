package stu.lab3;

import java.util.logging.Level;
import java.util.logging.LogManager;

import stu.lab3.database.InsertData;
import stu.lab3.database.SelectData;
import stu.lab3.util.HibernateUtil;
import stu.lab3.util.Menu;

public class Runner {

    public static void main(String[] args) {
        // disables Hibernate Warn and Info messages
        LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
        //Initialize SessionFactory
        HibernateUtil.getSessionFactory();
        mainMenu();
        shutdown();
    }

    // terminate session factory and entity manager to stop program properly
    public static void shutdown() {
        HibernateUtil.getEntityManager().close();
        HibernateUtil.getSessionFactory().close();
    }

    public static void mainMenu() {
        new Menu("Choose operation:")
                .addOption("Show data", SelectData::showMenu)
                .addOption("Add data", InsertData::addMenu)
                .addExit(Runner::shutdown).takeOneInput();
    }

    public static void returnMenu() {
        new Menu().addOption("Back to start", Runner::mainMenu)
                .addExit(Runner::shutdown).takeOneInput();
    }

}
