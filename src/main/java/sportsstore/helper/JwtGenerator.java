package sportsstore.helper;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import sportsstore.dto.UserDTO;

public class JwtGenerator implements IJwtGenerator {
    Date currentDate;
    LocalDateTime localDateTime;
    String secretString = "prefix_to_make_the_key_longer_super_ultra_mega_long_ass_secret_key";

    public JwtGenerator() {
        currentDate = new Date();
        localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Override
    public String createJWT(UserDTO user) {
        // TODO Auto-generated method stub

        // create signing Key
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretString);
        Key key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // create jwt builder
        JwtBuilder builder = Jwts.builder().setId(user.getId()).setIssuedAt(new Date()).setSubject(user.getEmail())
                .setIssuer(user.getUserName())
                .setExpiration(Date.from(localDateTime.plusDays(7).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key);

        return builder.compact();
    }

    @Override
    public boolean decodeJWT(String jwt, String email) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        if (Jwts.parserBuilder().setSigningKey(DatatypeConverter.parseBase64Binary(secretString)).build()
                .parseClaimsJws(jwt).getBody().getSubject().equals(email))
            return true;
        return false;
    }
}