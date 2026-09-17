package com.bibliproject.biblioteca.infrastructure.security;

import com.bibliproject.biblioteca.domain.auth.AuthenticatedPrincipal;
import com.bibliproject.biblioteca.domain.auth.TokenProvider;
import com.bibliproject.biblioteca.domain.user.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenProvider implements TokenProvider {

    private static final String CLAIM_USER_ID = "userId";
    private static final String CLAIM_ROLE = "role";
    private static final String CLAIM_STUDENT_ID = "studentId";

    private final SecretKey signingKey;
    private final long expirationSeconds;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                             @Value("${jwt.expiration-seconds:3600}") long expirationSeconds) {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationSeconds = expirationSeconds;
    }

    @Override
    public String generate(AuthenticatedPrincipal principal) {
        Instant now = Instant.now();
        var builder = Jwts.builder()
                .subject(principal.email())
                .claim(CLAIM_USER_ID, principal.userId())
                .claim(CLAIM_ROLE, principal.role().name())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(expirationSeconds)));
        if (principal.studentId() != null) {
            builder.claim(CLAIM_STUDENT_ID, principal.studentId());
        }
        return builder.signWith(signingKey).compact();
    }

    @Override
    public AuthenticatedPrincipal parse(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return new AuthenticatedPrincipal(
                toLong(claims.get(CLAIM_USER_ID)),
                claims.getSubject(),
                Role.valueOf(claims.get(CLAIM_ROLE, String.class)),
                toLong(claims.get(CLAIM_STUDENT_ID))
        );
    }

    private static Long toLong(Object value) {
        return value instanceof Number number ? number.longValue() : null;
    }
}
