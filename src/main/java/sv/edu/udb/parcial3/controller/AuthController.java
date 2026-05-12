package sv.edu.udb.parcial3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    @Operation(
            summary = "Inicio de sesión",
            description = "A partir de credenciales del usuario brinda token de autenticación",
            tags = "Autenticación"
    )
    public AuthResponse login(@Valid @RequestBody AuthRequest authRequest) {
        return authService.generarToken(authRequest);
    }

    @Operation(
            summary = "Registro de usuario",
            description = "Permite el registro de un nuevo usuario",
            tags = "Autenticación"
    )
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registrarUsario(@Valid @RequestBody UserRequest usuario) {
        return userService.registrarUsuario(usuario);
    }
}
