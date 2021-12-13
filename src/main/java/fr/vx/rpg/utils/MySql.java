package fr.vx.rpg.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql
{

    private static Connection conn;

    public static void connect() {
        if(!isConnected()) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rpg", "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void disconnect() {
        if(isConnected()) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean isConnected() {
        try {
            if((conn == null) || (conn.isClosed()) || conn.isValid(5)) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Connection getConnection() {
        return conn;
    }
}
