package sportsstore.helper.Authentication;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sportsstore.bo.UserBO;
import sportsstore.dto.UserDTO;

public class JwtGenerator implements IJwtGenerator {
    Date currentDate;
    LocalDateTime localDateTime;
    static String secretString = "prefix_to_make_the_key_longer_super_ultra_mega_long_ass_secret_key";

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

    // Authenticate part

    public static UserDTO getUserFromToken(String token) {
        UserBO userBO = null;
        Claims claims = decodeJWT(token);
        try {
            userBO = new UserBO();
            UserDTO user = userBO.getUserFromEmail(claims.getSubject());
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private static Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = decodeJWT(token);
        return claimsResolver.apply(claims);
    }

    private static Claims decodeJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        return Jwts.parserBuilder() // Configured and then used to parse JWT strings
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretString)).build() // Sets the signing key used
                                                                                          // to verify
                // any discovered JWS digital
                // signature
                .parseClaimsJws(jwt) // Parses the specified compact serialized JWS string based
                .getBody();
    }
}