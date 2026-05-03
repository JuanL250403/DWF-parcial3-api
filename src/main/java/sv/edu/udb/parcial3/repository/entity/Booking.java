package sv.edu.udb.parcial3.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "bookings")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Long id;

    @Column(nullable = false)
    private int cantidad;

    private double cobroTotal;

    @Builder.Default
    private LocalDateTime fechaReserva = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusBooking estatus;

    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne()
    @JoinColumn(name = "evento_id")
    private Event evento;
}
