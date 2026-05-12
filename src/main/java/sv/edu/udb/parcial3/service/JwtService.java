package sv.edu.udb.parcial3.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sv.edu.udb.parcial3.repository.entity.User;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiracion;

    @Value("${jwt.refresh-expiration}")
    private Long refrescoExpiracion;

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String generarToken(User user){
        HashMap<String, Object> detalles = new HashMap<>();
        detalles.put("id", user.getIdUser());

        return buildToken(detalles, user, expiracion);
    }

    public String genrarRefreshToken(User user){
        return buildToken(new HashMap<>(), user, refrescoExpiracion);
    }

    public boolean tokenValido(String token, UserDetails userDetails){
        String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !tokenExpirado(token));
    }

    public boolean tokenExpirado(String token){
        Date expiracion = extractExpiracion(token);
        return expiracion.before(new Date());
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiracion(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private String buildToken(Map<String, Object> extraclaims, User userDetails, long expiracion) {
        return Jwts.builder()
                .setClaims(extraclaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(getSecretKey())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
