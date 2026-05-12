package sv.edu.udb.parcial3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.parcial3.controller.request.EventRequest;
import sv.edu.udb.parcial3.controller.response.EventResponse;
import sv.edu.udb.parcial3.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@SecurityRequirement(name = "Auth")
public class EventController {
    private final EventService eventService;

    @Operation(
            summary = "Obtener todos los eventos",
            description = "Obtiene todos los eventos disponibles",
            tags = "Event"
    )
    @GetMapping
    public List<EventResponse> obtenerEventos() {
        return eventService.obtenerEventos();
    }

    @Operation(
            summary = "Obtener un evento",
            description = "Obtiene un evento por su ID",
            tags = "Event"
    )
    @GetMapping("/{id}")
    public EventResponse obtenerEvento(@Valid @PathVariable("id") Long id) {
        return eventService.obtenerEvento(id);
    }

    @Operation(
            summary = "Registro de evento",
            description = "Permite registrar un nuevo evento",
            tags = "Event"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse registrarEvento(@Valid @RequestBody EventRequest evento) {
        return eventService.registrarEvneto(evento);
    }

    @Operation(
            summary = "Editar evento",
            description = "Permite editar un evento a través de su ID",
            tags = "Event"
    )
    @PutMapping("/{id}")
    public EventResponse modificarEvento(@Valid @PathVariable("id") Long id, @RequestBody EventRequest evento) {
        return eventService.modificarEvento(id, evento);
    }

    @Operation(
            summary = "Cancelar evento",
            description = "Permite cancelar un evento a través de su ID",
            tags = "Event"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEvento(@PathVariable("id") Long id){
        eventService.eliminarEvento(id);
    }
}
