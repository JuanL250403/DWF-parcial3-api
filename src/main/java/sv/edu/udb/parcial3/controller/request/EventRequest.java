package sv.edu.udb.parcial3.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class EventRequest {
    private  String titulo;

    private String descripcion;

    private LocalDateTime fechaEvento;

    private String direccion;

    private int capacidad;

    private double precioPorTicket;
}
