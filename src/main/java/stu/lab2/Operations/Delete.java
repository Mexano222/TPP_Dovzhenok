package stu.lab2.Operations;

import stu.lab2.Database;

import java.sql.SQLException;

public class Delete extends Function {

    public Delete(String table, String field, String data) {
        super(table, field, data);
    }

    public void call(Database db) {
        //DELETE FROM table WHERE field = 'data'
        try {
            db.executeUpdate("DELETE FROM " + table + " WHERE " + fields.get(0) + " = '" + data.get(0) + "'");
            System.out.println("Deleted successfully");
        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
