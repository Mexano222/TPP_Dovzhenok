package stu.lab2.Operations;

import stu.lab2.Database;

import java.sql.SQLException;
import java.util.List;

public class Update extends Function {

    public Update(String table, String field, List<String> data) {
        super(table, field, data);
    }

    public void call(Database db) {
        //UPDATE table SET field = 'dataNew' WHERE field = 'dataOld'
        try {
            db.execute("UPDATE " + table + " SET " + fields.get(0) + " = '" + data.get(1) + "' WHERE " + fields.get(0) + " = '" + data.get(0) + "'");
            System.out.println("Updated successfully");
        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
