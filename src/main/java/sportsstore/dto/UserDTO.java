package sportsstore.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserDTO")
public class UserDTO {
    private String id;
    private String userName;
    private String email;
    private String password;
    private boolean isAdmin;

    public String getId() {
        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(String id) {
        this.id = id;
    }
}