package sv.edu.udb.parcial3.controller.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "El nombre de usuario es requerido")
    private String usuario;
    @NotBlank(message = "La contraseña es requerida")
    private String contrasenia;
}
