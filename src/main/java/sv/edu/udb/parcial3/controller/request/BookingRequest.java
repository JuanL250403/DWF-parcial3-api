package sv.edu.udb.parcial3.controller.request;

import lombok.Data;
import sv.edu.udb.parcial3.repository.entity.StatusBooking;

import java.time.LocalDateTime;

@Data
public class BookingRequest {
    private int cantidad;

    private Long eventoId;
}
