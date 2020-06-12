/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Max
 */
public class DBConnectionService {
    public static Connection getConnection() throws NamingException, SQLException {
        Connection conn = null;
        try {
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            String URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
            String username = "root";
            String password = "123456";
            
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL, username, password);
            System.out.println("DB connected successfully!");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            return conn;
        }
    }
}
