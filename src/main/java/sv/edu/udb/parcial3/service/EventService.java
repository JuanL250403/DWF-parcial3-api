package sv.edu.udb.parcial3.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.parcial3.controller.request.EventRequest;
import sv.edu.udb.parcial3.controller.response.EventResponse;
import sv.edu.udb.parcial3.repository.EventRepository;
import sv.edu.udb.parcial3.repository.UserRepository;
import sv.edu.udb.parcial3.repository.entity.Booking;
import sv.edu.udb.parcial3.repository.entity.Event;
import sv.edu.udb.parcial3.repository.entity.StatusBooking;
import sv.edu.udb.parcial3.repository.entity.User;
import sv.edu.udb.parcial3.service.mapper.EventMapper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventMapper eventMapper;

    public List<EventResponse> obtenerEventos() {
        List<Event> eventos = eventRepository.findAll();

        return eventMapper.toEventResponseList(eventos);
    }

    public EventResponse obtenerEvento(Long id) {
        Event evento = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));
        return eventMapper.toEventResponse(evento);
    }

    public EventResponse registrarEvneto(EventRequest evento) {
        Event eventoCrear = eventMapper.toEvent(evento);

        Event eventoCreado = eventRepository.save(eventoCrear);

        return eventMapper.toEventResponse(eventoCreado);
    }

    public EventResponse modificarEvento(Long id, EventRequest evento) {
        Event eventoMoidificar = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));

        eventoMoidificar.setTitulo(evento.getTitulo());
        eventoMoidificar.setDescripcion(evento.getDescripcion());
        eventoMoidificar.setFechaEvento(evento.getFechaEvento());
        eventoMoidificar.setCapacidad(evento.getCapacidad());
        eventoMoidificar.setDireccion(evento.getDireccion());
        eventoMoidificar.setPrecioPorTicket(evento.getPrecioPorTicket());

        Event eventoModificado = eventRepository.save(eventoMoidificar);

        return eventMapper.toEventResponse(eventoModificado);
    }

    public void eliminarEvento(Long id) {
        Event eventoEliminar = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Evento no encontrado"));

        eventoEliminar.setVigente(false);

        List<Booking> reservas = eventoEliminar.getReservas().stream()
                .map(r -> {
                    r.setEstatus(StatusBooking.CANCELADA);

                    return r;
                })
                .collect(Collectors.toList());

        eventoEliminar.setReservas(reservas);

        eventRepository.save(eventoEliminar);
    }

}
