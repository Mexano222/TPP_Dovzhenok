package stu.lab2.Operations;

import stu.lab2.Database;

import java.sql.SQLException;
import java.util.List;

public class Insert extends Function {

    public Insert(String table, List<String> fields, List<String> data) {
        super(table, fields, data);
    }

    public void call(Database db) {
        //INSERT INTO table (field1, field2) VALUES (data1, data2)
        try {
            db.execute("INSERT INTO " + table + "(" + String.join(", ", fields) + ") VALUES(" + String.join(", ", data) + ")");
            System.out.println("Inserted successfully");
        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
