package sv.edu.udb.parcial3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.udb.parcial3.controller.request.AuthRequest;
import sv.edu.udb.parcial3.controller.request.UserRequest;
import sv.edu.udb.parcial3.controller.response.AuthResponse;
import sv.edu.udb.parcial3.controller.response.UserResponse;
import sv.edu.udb.parcial3.service.AuthService;
import sv.edu.udb.parcial3.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.generarToken(authRequest);
    }

    @PostMapping("/register")
    public UserResponse registrarUsario(@RequestBody UserRequest usuario) {
        return userService.registrarUsuario(usuario);
    }
}
