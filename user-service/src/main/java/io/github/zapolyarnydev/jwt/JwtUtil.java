package io.github.zapolyarnydev.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {

    private static final String PRIVATE_KEY_PEM =
                    "-----BEGIN PRIVATE KEY-----\n" +
                            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC4f" +
                            "7OaJOMcGYLztHk7rIPAoUbiTlK+OEUVVnZOCOHPRIiCfnovQrBhrthQn7uAB4xMnD0wBV1kFIvakbsES//a5sA2iObTTg2zHLDBpKRMtd" +
                            "oEZ+3fbQz+eBjKMocomW9wkEkmIDiqGFSnz4PchoRZ9WrJuHm6Q5Tade54Zn9A0zLXDa8BJxSLFze7OceU+IGGW" +
                            "4WZLSEk4vxgkISJkNBpPhM9yz2eprBlQ+SoxqQbeP3dDhzwU0C2gmO21w3//DtbX0ypiw6Buq" +
                            "tFXRXrIr4QrQqFGCqKcYDMnpz3am+rnJFW9vMiYN2ir7O0Qzv0gpQdESHakU7NmfSsFh2YnYr3AgMBAAECggEAPoOWmX4yLc0/H9Bg26Vipxx+ZiPfyXBLiCVZxKshyBcSohS01dqk5" +
                            "RRXW0okQAdOtI6j9Od8c3K6yH5kfdo6gTN6oX/VpndmEPCBu7tLqGpdyuxkHzZWFsseqzRf" +
                            "s5P5vIxHHNICITBJTurzZP93TPvpCRymskQ/5Z3cBZPFPnM90A5pdAzNns5LUoSo7QlsHoqS2qR8jV4nkHSafSuUiFJot8s2q/4kZAyRzW3hQS37dE8mhUMCWKAAAg3AnBfyFYgSVMkWjQp+7" +
                            "PUiWiV2ZGUJv0tVlwNRVQDC14JxVbNKy7SMOJDpkrhAzIziMvAN8EN92h3uJ6dAaWjtHwFh4QKBgQDnVgShQ" +
                            "RwAC9ZzomguYtMnfVGHKn1XX9j2oEuMGetawD1clRxZseP20wfCrJVeuJVF1yfPDUyFuDsbInlsFWfuEj8zXhsr0RT5op5HggbhHHAeD+mu2bJYNh7eez79rRLYYePCRvueYRcgHyGZ1tckyh9O" +
                            "q1qqb5wN3bJsudQiuQKBgQDMK1Sd2Zw/6ugEzuAtP06t5LdvMHbB" +
                            "PYaoLez/bMlW50qfKWMexmhiQLfJyvJdNv+EoEIVfCzZEEgnAhiR0w4QHzTOME5MonpwjXaTwyXdEiwdFK0fyCl4OPTUFg8q+kPmk0aEJgRz8gF9maGRJevMp4YOVGgYiEMGdKMFmtsDLwKBgQDJe+ncaNDyWV0WqcUDYZ93LXbsut0gVKKra0FhbNHH4NeQ424l0QIz3+lKbPZL4Yhho9tQQsz7SlJwd74" +
                            "5nQtAFzRkkPopV5llM3WX4nALcLFV/ZYSbBkhK0IHlGBwoWVggSHV++z7LKTvnS2ux6fqqwBJjIUTHNJWYVkdyVwyQQKBgBAowcq4hXBdpD11LjMGlrCWsJoBSPTkbLr035QbxRe2uKY+PVUbOPnFy4YSJPqRtsmFcZWHvXj0m/FSbiVkgfFuttw24IuaNHyiBaeOQ3nqzUkD8OvaCMY2I9KBaHPrWOJv953P8HxIxCtvtGTrjm0" +
                            "lOSv7LD380uk45Ao2hm0LAoGAaH0GuvgrqgDMPYuE3m+JY+84rE+qwj/IrYOBHgVTeWIgYVvbutuvcx6980+mDRzemTGt81UA5UInMPHuoRGDHZ48VJoMmpVcxCkKCPpiFy07MrcK2f2vjkkPWeFbvGHCrcKVxuhs4YpjBZpFIGklLBDUK5zEy1Fda7/4VCcWLvs=\n" +
                            "-----END PRIVATE KEY-----";

    private static final String PUBLIC_KEY_PEM =
                    "-----BEGIN PUBLIC KEY-----\n" +
                            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuH+zmiTjHBmC87R5O6yDwKFG4k5SvjhFFVZ2Tgjhz0SIgn56L0KwYa7YUJ+7gAeMTJw9M" +
                            "AVdZBSL2pG7BEv/2ubANojm004NsxywwaSkTLXaBGft320M/ngYyjKHKJlv" +
                            "cJBJJiA4qhhUp8+D3IaEWfVqybh5ukOU2nXueGZ/QNMy1w2vAScUixc3uznHlPiBhluFmS0hJOL8YJCEiZDQaT4TPcs9nqawZUPkqMakG3j93Q4c8FNAtoJjttcN//w7W19MqYsOgbqrR" +
                            "V0V6yK+EK0KhRgqinGAzJ6c92pvq5yRVvbzImDdoq+ztEM79IKUHREh2pFOzZn0rBYdmJ2K9wIDAQAB\n" +
                            "-----END PUBLIC KEY-----";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    private static final PrivateKey privateKey = loadPrivateKey();
    private static final PublicKey publicKey = loadPublicKey();

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
            return false;
        }
    }

    private static PrivateKey loadPrivateKey() {
        try {
            String key = PRIVATE_KEY_PEM.replaceAll("-----\\w+ PRIVATE KEY-----", "").replaceAll("\\s", "");
            byte[] decoded = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки закрытого ключа", e);
        }
    }

    private static PublicKey loadPublicKey() {
        try {
            String key = PUBLIC_KEY_PEM.replaceAll("-----\\w+ PUBLIC KEY-----", "").replaceAll("\\s", "");
            byte[] decoded = Base64.getDecoder().decode(key);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки открытого ключа", e);
        }
    }
}
