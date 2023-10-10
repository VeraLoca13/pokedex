package com.vera.pokedex;

import com.vera.pokedex.util.R;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBCon {
    private Connection con;

    public void connect() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        System.out.println(R.getProperties("database.properties"));
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }

    public void disconnect() throws SQLException {
        con.close();
    }
}
