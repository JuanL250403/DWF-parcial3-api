package sv.edu.udb.parcial3.controller.response;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserResponse {
    private String nombreUsuario;

    private String email;
    private String nombre;
    private String apellido;
    private Integer edad;
}
