package sportsstore.bo;

import java.util.List;

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

    public UserDTO checkPassAndEmail(String email, String plainPassword) throws Exception {
        UserDAO userDAO = null;

        try {
            userDAO = new UserDAO();
            UserDTO userDTO = userDAO.getUserFromEmail(email);
            if (userDTO != null) {
                if (checkPass(plainPassword, userDTO.getPassword()))
                    return userDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userDAO.closeConnection();
        }
        return null;
    }

    public UserDTO createUser(String userName, String email, String plainPassword) throws Exception {
        UserDAO userDAO = null;

        try {
            userDAO = new UserDAO();
            UserDTO userDTO = userDAO.getUserFromEmail(email);
            if (userDTO.getUserName() == null || userDTO.getUserName().isEmpty()) {
                if (userDAO.createUser(userName, email, hashPassword(plainPassword))) {
                    UserDTO result = userDAO.getUserFromEmail(email);
                    if (result != null)
                        return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userDAO.closeConnection();
        }
        return null;
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

    public List<UserDTO> getAllEmployees() throws Exception {
        UserDAO userDAO = null;
        try {
            userDAO = new UserDAO();

            List<UserDTO> result = userDAO.getEmployees();
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            userDAO.closeConnection();
        }
    }
}