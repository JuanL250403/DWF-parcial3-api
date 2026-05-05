package sv.edu.udb.parcial3.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import sv.edu.udb.parcial3.repository.entity.StatusBooking;

import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @NotNull(message = "La cantidad ha reservar es requerida")
    @Positive(message = "La cantidad ha reservar debe ser mayor a 0")
    private int cantidad;

    @NotNull(message = "El evento a reservar es requerido")
    private Long eventoId;
}
