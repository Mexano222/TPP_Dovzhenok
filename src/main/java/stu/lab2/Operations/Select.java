package stu.lab2.Operations;

import stu.lab2.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Select extends Function {

    public Select(String table, String field, String data) {
        super(table, field, data);
    }

    public void call(Database db) {
        //SELECT * FROM table WHERE field = 'data'
        try {
            ResultSet resultSet = db.execute("SELECT * FROM " + table + " WHERE " + fields.get(0) + " = '" + data.get(0) + "'");
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                System.out.printf("%-17s", resultSet.getMetaData().getColumnName(i));
            }
            System.out.println();
            resultSet.next();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                System.out.printf("%-17s", resultSet.getString(i));
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
