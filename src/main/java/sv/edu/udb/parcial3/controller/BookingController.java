package sv.edu.udb.parcial3.controller;

import lombok.RequiredArgsConstructor;
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
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/my")
    public Object obtenerReservasUsuario(){
        return  bookingService.obtenerReservasUsuario();
    }

    @PostMapping
    public BookingResponse registrarReserva(@RequestBody BookingRequest reserva){
        return bookingService.registrarReserva(1L, reserva);
    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable("id") Long id){
        bookingService.cancelarReserva(id);
    }
}
