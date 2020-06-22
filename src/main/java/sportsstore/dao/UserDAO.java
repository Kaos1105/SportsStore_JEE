package sportsstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import sportsstore.dto.UserDTO;

public class UserDAO extends AbstractDAO {
    public UserDAO() throws Exception {

    }

    public UserDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }

    public void writeUserDTO(UserDTO userDTO, ResultSet rs) throws Exception {
        userDTO.setId(rs.getString("id"));
        userDTO.setUserName(rs.getString("UserName"));
        userDTO.setEmail(rs.getString("Email"));
        userDTO.setPassword(rs.getString("Password"));
    }

    public UserDTO getUserFromName(String userName) throws Exception {
        UserDTO userDTO = new UserDTO();

        try {
            String query = "select * from Users where UserName=N'" + userName + "'";
            ResultSet rs = UserDAO.super.ExecuteQuery(query, null);
            if (rs.next()) {
                writeUserDTO(userDTO, rs);
            }
        } catch (Exception e) {
            // System.out.println(e.toString());
            e.printStackTrace();
        }
        return userDTO;
    }

    public UserDTO getUserFromEmail(String email) throws Exception {
        UserDTO userDTO = new UserDTO();

        try {
            String query = "select * from Users where Email=N'" + email + "'";
            ;
            ResultSet rs = UserDAO.super.ExecuteQuery(query, null);
            if (rs.next()) {
                writeUserDTO(userDTO, rs);
            }
        } catch (Exception e) {
            // System.out.println(e.toString());
            e.printStackTrace();
        }
        return userDTO;
    }

    public boolean createUser(String userName, String email, String hashedPassword) throws Exception {
        try {
            String query = "EXEC USP_InsertUser ? , ? , ?";
            if (UserDAO.super.ExecuteNonQuery(query, new Object[] { userName, email, hashedPassword }) == 1)
                return true;
        } catch (Exception e) {
            // System.out.println(e.toString());
            e.printStackTrace();
        }
        return false;
    }
}