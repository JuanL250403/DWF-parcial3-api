package sv.edu.udb.parcial3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.parcial3.controller.request.UserRequest;
import sv.edu.udb.parcial3.controller.response.UserResponse;
import sv.edu.udb.parcial3.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserResponse> obtenerUsuarios() {
        return userService.obtenerUsuarios();
    }

}
