package task.tracker.backend.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import task.tracker.backend.model.User;

import java.security.Key;
import java.util.Date;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class JwtService {

    @Value("${jwt.secret}")
    String secretKey;

    @Value("${jwt.expiration-time}")
    long expirationTime;

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token expired", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT", e);
        } catch (MalformedJwtException e) {
            log.error("Malformed JWT", e);
        } catch (IllegalArgumentException e) {
            log.error("Invalid token", e);
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
