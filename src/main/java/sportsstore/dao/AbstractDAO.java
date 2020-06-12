/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportsstore.dao;

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
 * @author Max
 */
public abstract class AbstractDAO {
    protected Connection connection;
    
    public AbstractDAO() throws Exception {
        try {
            connection = DBConnectionService.getConnection();
        }
        catch (Exception e) {
            System.err.println("Failed in constructor method in AbstractDAO:" + e);
            e.printStackTrace();
            throw e;
        }
    }
    
    public AbstractDAO(Connection conn) {
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
}
