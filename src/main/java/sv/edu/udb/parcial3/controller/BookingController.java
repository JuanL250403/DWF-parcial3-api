package sv.edu.udb.parcial3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.parcial3.controller.request.BookingRequest;
import sv.edu.udb.parcial3.controller.response.BookingResponse;
import sv.edu.udb.parcial3.repository.entity.User;
import sv.edu.udb.parcial3.service.BookingService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@SecurityRequirement(name = "Auth")
public class BookingController {
    private final BookingService bookingService;

    @Operation(
            summary = "Listar reservas de usuario",
            description = "Obtiene todas las reservas de un usuario",
            tags = "Booking"
    )
    @GetMapping("/my")
    public Object obtenerReservasUsuario() {
        return bookingService.obtenerReservasUsuario();
    }

    @Operation(
            summary = "Registrar reserva",
            description = "Permite registrar una reserva hacia un evento",
            tags = "Booking"
    )

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse registrarReserva(@Valid @RequestBody BookingRequest reserva) {
        return bookingService.registrarReserva(reserva);
    }

    @Operation(
            summary = "Eliminar una reserva",
            description = "Elimina una reserva a través de su ID",
            tags = "Booking"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarReserva(@PathVariable("id") Long id) {
        bookingService.cancelarReserva(id);
    }
}
