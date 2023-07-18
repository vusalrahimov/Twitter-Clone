package com.twitter.twitterclone.db;

import lombok.SneakyThrows;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    @SneakyThrows
    public static Connection getConnection(){
        Connection connection = null;
        try {
        Context ctx = new InitialContext();

        DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql");
        connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (NamingException e){
            throw new RuntimeException(e);
        }
        return connection;
    }

}
