package com.orangelabs.RestaurantDemo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtService {

    private String secret;
    private Long expiration;
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expiration}") Long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String generateToken(String email) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(email)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 10000);
    }
    
    /////
    
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenNotExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.after(new Date());
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException error) {
            logger.error("Invalid JWT signature -> Message: {} ", error);
        } catch (MalformedJwtException error) {
            logger.error("Invalid JWT token -> Message: {}", error);
        } catch (ExpiredJwtException error) {
            logger.error("Expired JWT token -> Message: {}", error);
        } catch (UnsupportedJwtException error) {
            logger.error("Unsupported JWT token -> Message: {}", error);
        } catch (IllegalArgumentException error) {
            logger.error("JWT claims string is empty -> Message: {}", error);
        }
        
        return false;
    }
	
}