package sv.edu.udb.parcial3.controller.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class EventRequest {
    @NotBlank(message = "El titulo es requerido")
    private  String titulo;

    @NotBlank(message = "La descripción es requerida")
    @Size(max = 255, message = "La descripcion no puede tener mas de 255 carácteres")
    private String descripcion;

    @FutureOrPresent(message = "la fecha del evento no es valida")
    @NotNull(message = "La fecha es requerida")
    private LocalDateTime fechaEvento;

    @NotBlank(message = "La dirección es requerida")
    @Size(max = 125, message = "La descripcion no puede tener mas de 125 carácteres")
    private String direccion;

    @NotNull(message = "La capacidad es requerida")
    @Positive(message = "La capacidad del evento debe de ser superior a 0")
    private int capacidad;

    @NotNull(message = "El precio por ticket es requerido")
    @Positive(message = "El precio por ticket debe ser mayor a 0")
    private double precioPorTicket;
}
