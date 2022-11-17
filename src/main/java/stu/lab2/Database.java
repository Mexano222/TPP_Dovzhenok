package stu.lab2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class Database  {
    private final String url = "jdbc:postgresql://192.168.126.131/postgres?user=postgres&password=postgres";
    Connection conn;

    public Database() throws SQLException {
        conn = getConnection(url);
    }

    public void reconnect() throws SQLException {
        if(!conn.isClosed())
            conn.close();
        conn = getConnection(url);
    }

    public ResultSet executeQuery(String query) throws SQLException {
        if(conn.isClosed())
            reconnect();
        Statement st = conn.createStatement();
        return st.executeQuery(query);
    }

    public void executeUpdate(String query) throws SQLException {
        if(conn.isClosed())
            reconnect();
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }
}
