package sv.edu.udb.parcial3.controller.request;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import sv.edu.udb.parcial3.repository.entity.Booking;

import java.util.List;

@Data
public class UserRequest {
    @NotBlank(message = "El nombre de usuario es requerido")
    private String nombreUsuario;

    @Email(message = "Formato de correo electrónico no valido")
    private String email;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El apellido es requerido")
    private String apellido;

    @NotNull(message = "La edad es requerida")
    @Min(value = 15, message = "La edad minima permitida son 15 años")
    private Integer edad;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 6, message = "La contraseña debe tener almenos 6 carácteres")
    private String contrasenia;
}
