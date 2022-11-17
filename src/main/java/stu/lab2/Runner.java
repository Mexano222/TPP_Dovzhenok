package stu.lab2;

import stu.lab2.Operations.*;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.IntStream;

public class Runner {

    public static void main(String[] args) throws SQLException {
        Database db = new Database();

        // Let the user select one of the available operations
        String operation = callMenu(
                "Choose function:",
                Arrays.asList(
                        "Select",
                        "Update",
                        "Insert",
                        "Delete"
                ));

        // Let the user select table to execute selected operation
        System.out.println("Enter table:");
        Scanner sc = new Scanner(System.in);
        String table = sc.nextLine();

        if (Arrays.asList("Select", "Delete").contains(operation)) {// Let the user select a field to SELECT data by its content
            runSelectDelete(db, operation, sc, table);
        } else if ("Update".equals(operation)) {// Let the user select a field to UPDATE data in it
            runUpdate(db, sc, table);
        } else if ("Insert".equals(operation)) {// Let the user select insert data
            runInsert(db, sc, table);
        }

        sc.close();
        if (!db.conn.isClosed())
            db.conn.close();
    }

    private static void runInsert(Database db, Scanner sc, String table) {
        System.out.println("Enter fields to insert into them:");
        List<String> fields = Arrays.asList(sc.nextLine().split(","));
        System.out.println("Enter data to insert:");
        List<String> data = Arrays.asList(sc.nextLine().split(","));
        new Insert(table, fields, data).call(db);
    }

    private static void runUpdate(Database db, Scanner sc, String table) {
        System.out.println("Enter field to update it:");
        String field = sc.nextLine();
        System.out.println("Enter data to find:");
        List<String> data = new ArrayList<>();
        data.add(sc.nextLine());
        System.out.println("Enter new data:");
        data.add(sc.nextLine());
        new Update(table, field, data).call(db);
    }

    private static void runSelectDelete(Database db, String operation, Scanner sc, String table) {
        System.out.println("Enter field to search by it:");
        String field = sc.nextLine();
        System.out.println("Enter data to find:");
        String data = sc.nextLine();
        if (operation.equals("Delete")) {
            new Delete(table, field, data).call(db);
            return;
        }
        new Select(table, field, data).call(db);
    }

    static String callMenu(String title, List<String> menu) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println(title);
            System.out.println("--------------");
            IntStream.range(0, menu.size()).mapToObj(i -> "\t" + (i + 1) + ". " + menu.get(i)).forEach(System.out::println);
            System.out.println("--------------");
            System.out.println("Enter your choice:");
            try {
                option = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
            }
        } while ((option < 1 || option > menu.size()));
        return menu.get(option - 1);
    }
}
