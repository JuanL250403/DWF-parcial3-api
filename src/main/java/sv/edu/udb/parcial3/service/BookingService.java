package sv.edu.udb.parcial3.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sv.edu.udb.parcial3.exception.DisponibilidadSuperadaException;
import sv.edu.udb.parcial3.controller.request.BookingRequest;
import sv.edu.udb.parcial3.controller.response.BookingResponse;
import sv.edu.udb.parcial3.repository.BookingRepository;
import sv.edu.udb.parcial3.repository.EventRepository;
import sv.edu.udb.parcial3.repository.UserRepository;
import sv.edu.udb.parcial3.repository.entity.Booking;
import sv.edu.udb.parcial3.repository.entity.Event;
import sv.edu.udb.parcial3.repository.entity.StatusBooking;
import sv.edu.udb.parcial3.repository.entity.User;
import sv.edu.udb.parcial3.service.mapper.BookingMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final BookingMapper bookingMapper;

    public List<BookingResponse> obtenerReservasUsuario(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User usuario = (User) authentication.getPrincipal();

        List<Booking> reservas= bookingRepository.reservasPorusuario(usuario.getIdUser());

        return bookingMapper.toBookingResponseList(reservas);
    }

    public List<BookingResponse> obtenerReservas() {
        List<Booking> reservas = bookingRepository.findAll();

        return bookingMapper.toBookingResponseList(reservas);
    }

    public BookingResponse registrarReserva(Long usuarioId, BookingRequest reserva) {
        int cantidadReserva = reserva.getCantidad();
        Event evento = eventRepository.findById(reserva.getEventoId()).orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));

        if (evento.getCapacidad() < cantidadReserva) {
            throw new DisponibilidadSuperadaException("La cantidad de entradas solicitadas supera a la cpacidad del evento");
        }

        User usuario = userRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        double precioPorTicket = evento.getPrecioPorTicket();
        Booking reservaCrear = Booking.builder()
                .usuario(usuario)
                .evento(evento)
                .cantidad(cantidadReserva)
                .cobroTotal(cantidadReserva * precioPorTicket)
                .estatus(StatusBooking.CONFIRMADA)
                .build();

        Booking reservaCreada = bookingRepository.save(reservaCrear);

        evento.setCapacidad(evento.getCapacidad() - cantidadReserva);

        eventRepository.save(evento);
        return bookingMapper.toBookingResponse(reservaCreada);
    }

    public void cancelarReserva(Long id) {
        Booking reservaCancelar = bookingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada"));

        if (StatusBooking.CANCELADA.equals(reservaCancelar.getEstatus())) {
            throw new EntityNotFoundException("Reserva no encontrada");
        }

        reservaCancelar.setEstatus(StatusBooking.CANCELADA);
        bookingRepository.save(reservaCancelar);
    }
}
