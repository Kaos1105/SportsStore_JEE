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
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

/**
 *
 * @author Max
 */
public abstract class AbstractDAO {
    protected Connection connection;

    public AbstractDAO() throws Exception {
        if (!CheckDatabaseExists("SportStoreDev")) {
            try {
                URL resourceCMD = getClass().getResource("/Resources");
                Path pathCMD = Paths.get(URI.create(resourceCMD.toString()));
                File dir = new File(pathCMD.toString());
                Process p = Runtime.getRuntime().exec("sqlcmd -S 127.0.0.1 -E -i Database.sql", null, dir);
                p.waitFor();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        try {
            connection = DBConnectionService.getConnection();
        } catch (Exception e) {
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

    private Boolean CheckDatabaseExists(String dbName) {
        try {
            Connection con = DBConnectionService.getConnection("master");
            ResultSet rs = null;
            if (con != null) {
                rs = con.getMetaData().getCatalogs();
                while (rs.next()) {
                    String catalogs = rs.getString(1);

                    if (dbName.equals(catalogs)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet ExecuteQuery(String query, Object[] params) {
        ResultSet rs;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            if (params != null) {
                String[] listParas = query.split(" ");
                int i = 0;
                for (String item : listParas) {
                    if (item.contains("?")) {
                        ps.setObject(i + 1, params[i]);
                        i++;
                    }
                }
            }
            rs = ps.executeQuery();
            // ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            rs = null;
        }
        return rs;
    }

    public int ExecuteNonQuery(String query, Object[] params) {
        int rowAffected = 0;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            if (params != null) {
                String[] listParas = query.split(" ");
                int i = 0;
                for (String item : listParas) {
                    if (item.contains("?")) {
                        if (params[i] != null) {
                            ps.setObject(i + 1, params[i]);
                        } else
                            ps.setNull(i + 1, Types.FLOAT);
                        i++;
                    }
                }
            }
            rowAffected = ps.executeUpdate();
            // ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowAffected;
    }

    // returns the first column of the first row in the result set
    public Object ExecuteScalar(String query, Object[] params) {
        ResultSet rs;
        Object result = null;
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            if (params != null) {
                String[] listParas = query.split(" ");
                int i = 0;
                for (String item : listParas) {
                    if (item.contains("?")) {
                        ps.setObject(i + 1, params[i]);
                        i++;
                    }
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getObject(1);
            }
            // ps.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
