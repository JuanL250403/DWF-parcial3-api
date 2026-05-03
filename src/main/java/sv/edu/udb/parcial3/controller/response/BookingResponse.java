package sv.edu.udb.parcial3.controller.response;

import lombok.Data;
import sv.edu.udb.parcial3.repository.entity.StatusBooking;

import java.time.LocalDateTime;

@Data
public class BookingResponse {

    private Long id;

    private int cantidad;

    private double cobroTotal;

    private LocalDateTime fechaReserva;

    private StatusBooking estatus;

    private String usuario;

    private String evento;
}
