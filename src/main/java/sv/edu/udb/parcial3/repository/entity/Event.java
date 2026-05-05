package sv.edu.udb.parcial3.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "events")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private Long id;

    @Column(nullable = false, length = 50)
    private  String titulo;

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fechaEvento;

    @Column(nullable = false, length = 125)
        private String direccion;

    @Column(nullable = false)
    private int capacidad;

    @Column(nullable = false)
    private double precioPorTicket;

    private boolean vigente = true;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.MERGE)
    private List<Booking> reservas;
}
