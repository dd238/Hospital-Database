package com.company;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL_Connection {

    Connection connection = null;

    public Connection MySQL_Connection()
    {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital_DB?useSSL=false", "####", "#####");
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("No Connection.");
            return null;
        }
    }

}
