package sv.edu.udb.parcial3.controller.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventResponse {

    private Long id;

    private  String titulo;

    private String descripcion;

    private LocalDateTime fechaEvento;

    private String direccion;

    private int capacidad;

    private double precioPorTicket;
}
