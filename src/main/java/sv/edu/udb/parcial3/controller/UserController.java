package sv.edu.udb.parcial3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.parcial3.controller.request.UserRequest;
import sv.edu.udb.parcial3.controller.response.UserResponse;
import sv.edu.udb.parcial3.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Auth")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Obtiene todos los usuarios registrados",
            tags = "User"
    )
    @GetMapping
    public List<UserResponse> obtenerUsuarios() {
        return userService.obtenerUsuarios();
    }

}
