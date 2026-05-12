package sv.edu.udb.parcial3.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sv.edu.udb.parcial3.controller.request.AuthRefreshRequest;
import sv.edu.udb.parcial3.controller.request.AuthRequest;
import sv.edu.udb.parcial3.controller.response.AuthRefreshResponse;
import sv.edu.udb.parcial3.controller.response.AuthResponse;
import sv.edu.udb.parcial3.repository.entity.User;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthResponse generarToken(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsuario(),
                        authRequest.getContrasenia()
                )
        );

        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            String jwtToken = jwtService.generarToken(user);
            String refreshToken = jwtService.genrarRefreshToken(user);

            return AuthResponse.builder()
                    .authToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();

        }

        throw new UsernameNotFoundException("Credenciales invalidas");
    }

    public AuthRefreshResponse refrescarToken(AuthRefreshRequest authRefreshRequest) {
        String refrescoJwt = authRefreshRequest.getRefreshToken();

        String username = jwtService.extractUsername(refrescoJwt);

        if (username != null) {
            User usuario = (User) userDetailsService.loadUserByUsername(username);

            if(!Objects.isNull(usuario)){
                String jwtToken = jwtService.generarToken(usuario);

                return AuthRefreshResponse.builder()
                        .authToken(jwtToken)
                        .build();

            }
        }
        throw new UsernameNotFoundException("Credenciales invalidas");
    }
}
