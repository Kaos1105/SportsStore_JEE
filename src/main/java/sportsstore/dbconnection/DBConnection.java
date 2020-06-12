/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.dbconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import sportsstore.dbconnection.DBConnectionService;

/**
 *
 * @author hoango7604
 */
public class DBConnection {
    private Connection connection;
    
    public DBConnection() throws Exception {
        try {
            connection = DBConnectionService.getConnection();
        }
        catch (Exception e) {
            System.err.println("Failed in constructor method in MapperDB:" + e);
            e.printStackTrace();
            throw e;
        }
    }
    
    public DBConnection(Connection conn) {
        connection = conn;
    }
    
    public void setConnection(Connection conn) {
        connection = conn;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection() throws Exception {
        try {
            getConnection().close();
        } catch (Exception e) {
            System.err.println("Failed in closeConnection method in MapperDB:" + e);
            e.printStackTrace();
            throw e;
        }
    }
    
    public Boolean execute(String query) {
        try {
            System.out.println(query);
            Statement stmt = connection.createStatement();
            stmt.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public CachedRowSet selectFromDb(String query) {
        try {
            //We need to store the results before closing the connection
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowset = factory.createCachedRowSet();

            Statement stmt = connection.createStatement();
            ResultSet results = stmt.executeQuery(query);
            rowset.populate(results);
            stmt.close();
            return rowset;
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
            return null;
        }
    }
}
