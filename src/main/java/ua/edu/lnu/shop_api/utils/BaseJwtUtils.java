package ua.edu.lnu.shop_api.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.edu.lnu.shop_api.entity.Role;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class BaseJwtUtils implements JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration lifetime;

    @Override
    public String generateToken(UUID id, String username, Role role) {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + lifetime.toMillis());

        return Jwts.builder()
                .setSubject(username)
                .claim("id", id)
                .setIssuer("shop")
                .claim("role", role)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty: {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("Signature validation failed.", e.getMessage());
        }
        return false;
    }

    @Override
    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build()
                .parseClaimsJws(token)
                .getBody();
    }
}
