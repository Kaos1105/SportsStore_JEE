package sportsstore.bo;

import org.mindrot.jbcrypt.BCrypt;

import sportsstore.dao.UserDAO;
import sportsstore.dto.UserDTO;

public class UserBO {

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        return false;
    }

    public boolean checkPassAndEmail(String email, String plainPassword) throws Exception {
        UserDAO userDAO = null;

        try {
            userDAO = new UserDAO();
            UserDTO userDTO = userDAO.getUserFromEmail(email);
            if (userDTO != null) {
                return checkPass(plainPassword, userDTO.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userDAO.closeConnection();
        }
        return false;
    }

    public boolean createUser(String userName, String email, String plainPassword) throws Exception {
        UserDAO userDAO = null;

        try {
            userDAO = new UserDAO();
            UserDTO userDTO = userDAO.getUserFromEmail(email);
            if (userDTO.getUserName() == null || userDTO.getUserName().isEmpty()) {
                return userDAO.createUser(userName, email, hashPassword(plainPassword));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userDAO.closeConnection();
        }
        return false;
    }

    public boolean setUserMain(String email) throws Exception {
        UserDAO userDAO = null;
        try {
            userDAO = new UserDAO();
            UserDTO userDTO = userDAO.getUserFromEmail(email);
            if (userDTO.getUserName() != null || !userDTO.getUserName().isEmpty()) {
                return userDAO.setAdmin(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userDAO.closeConnection();
        }
        return false;
    }

    public boolean deleteUser(String email) throws Exception {
        UserDAO userDAO = null;
        try {
            userDAO = new UserDAO();
            UserDTO userDTO = userDAO.getUserFromEmail(email);
            if (userDTO.getUserName() != null || !userDTO.getUserName().isEmpty()) {
                return userDAO.deleteUser(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userDAO.closeConnection();
        }
        return false;
    }

}