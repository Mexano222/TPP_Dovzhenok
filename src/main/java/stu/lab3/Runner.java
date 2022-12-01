package stu.lab3;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;

import stu.lab3.database.InsertData;
import stu.lab3.database.SelectData;
import stu.lab3.util.HibernateUtil;
import stu.lab3.util.Menu;

public class Runner {
    public static Scanner sysIn = new Scanner(System.in);

    public static void main(String[] args) {
        // disables Hibernate Warn and Info messages
        LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
        HibernateUtil.getSessionFactory();
        mainMenu();
        shutdown();
    }

    // terminate session factory and entity manager, otherwise program won't end
    public static void shutdown() {
        HibernateUtil.getEntityManager().close();
        HibernateUtil.getSessionFactory().close();
        sysIn.close();
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
