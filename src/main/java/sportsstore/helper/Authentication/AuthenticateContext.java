package sportsstore.helper.Authentication;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import sportsstore.dto.UserDTO;

public class AuthenticateContext implements SecurityContext {
    private UserDTO userDTO;
    private boolean secure;

    public AuthenticateContext(UserDTO user, boolean secure) {
        this.userDTO = user;
        this.secure = secure;
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> userDTO.getUserName();
    }

    @Override
    public boolean isSecure() {
        return secure;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

    @Override
    public boolean isUserInRole(String role) {
        return userDTO.getRole().equals(role);
    }
}