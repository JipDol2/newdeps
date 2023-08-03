package lotte.newdevps.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtManager {

    @Value("${jwt.secret}")
    private String KEY;

    private final static int ACCESS_TIME = 60 * 30 * 1000;  //10분

    private final static int REFRESH_TIME = 60 * 20 * 1000; //20분

    public String createAccessToken(Long id) {
        return createToken(id, ACCESS_TIME);
    }

    public String createRefreshToken(Long id) {
        return createToken(id, REFRESH_TIME);
    }

    private String createToken(Long id, int tokenTime) {
        return Jwts.builder()
                .claim("id", id)
                .signWith(getKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenTime))
                .compact();
    }

    public Claims getClaims(String jwt){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

    public String getId(String jwt){
        return String.valueOf(getClaims(jwt).get("id"));
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));
    }
}