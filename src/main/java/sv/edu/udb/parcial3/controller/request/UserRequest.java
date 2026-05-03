package sv.edu.udb.parcial3.controller.request;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.edu.udb.parcial3.repository.entity.Booking;

import java.util.List;

@Data
public class UserRequest {
    private String nombreUsuario;

    private String email;

    private String nombre;

    private String apellido;

    private Integer edad;

    private String contrasenia;
}
