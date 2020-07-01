package sportsstore.helper;

import sportsstore.dto.UserDTO;

public interface IJwtGenerator {
    String createJWT(UserDTO user);

    boolean decodeJWT(String jwt, String email);
}