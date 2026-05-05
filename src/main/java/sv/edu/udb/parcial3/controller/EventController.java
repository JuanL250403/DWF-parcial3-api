package sv.edu.udb.parcial3.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.parcial3.controller.request.EventRequest;
import sv.edu.udb.parcial3.controller.response.EventResponse;
import sv.edu.udb.parcial3.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public List<EventResponse> obtenerEventos() {
        return eventService.obtenerEventos();
    }

    @GetMapping("/{id}")
    public EventResponse obtenerEvento(@Valid @PathVariable("id") Long id) {
        return eventService.obtenerEvento(id);
    }

    @PostMapping
    public EventResponse registrarEvento(@Valid @RequestBody EventRequest evento) {
        return eventService.registrarEvneto(evento);
    }

    @PutMapping("/{id}")
    public EventResponse modificarEvento(@Valid @PathVariable("id") Long id, @RequestBody EventRequest evento) {
        return eventService.modificarEvento(id, evento);
    }

    @DeleteMapping("/{id}")
    public void eliminarEvento(@PathVariable("id") Long id){
        eventService.eliminarEvento(id);
    }
}
