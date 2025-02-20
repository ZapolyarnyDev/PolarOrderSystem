package io.github.zapolyarnydev.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

public class JwtUtil {

    private static final KeyPair keyPair = generateKeyPair();
    private static final PrivateKey privateKey = keyPair.getPrivate();
    private static final PublicKey publicKey = keyPair.getPublic();
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    private JwtUtil() {}

    public static String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }

    public static String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public static boolean validateToken(String token, String email) {
        try {
            return extractEmail(token).equals(email);
        } catch (JwtException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка генерации RSA ключа", e);
        }
    }
}
