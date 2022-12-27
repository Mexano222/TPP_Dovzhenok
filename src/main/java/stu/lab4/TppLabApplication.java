package stu.lab4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import stu.lab4.controller.InsertController;
import stu.lab4.controller.SelectController;
import stu.lab4.repo.AirportRepository;
import stu.lab4.util.Menu;

@SpringBootApplication
public class TppLabApplication {

    @Autowired
    SelectController selectController;
    @Autowired
    InsertController insertController;

    public static void main(String[] args) {
        SpringApplication.run(TppLabApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AirportRepository repository) {
        return (args) -> {
            //Initialize SessionFactory
            mainMenu();
        };
    }

    public void mainMenu() {
        int sel = new Menu("Choose operation:")
                .addOption("Show data")
                .addOption("Add data")
                .addOption("Exit")
                .takeOneInput();
        switch (sel) {
            case 0 ->
                selectController.showMenu();
            case 1 ->
                insertController.showMenu();
            case 2 ->
                System.exit(0);
        }
    }

}
