package sv.edu.udb.parcial3.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sv.edu.udb.parcial3.controller.response.BookingResponse;
import sv.edu.udb.parcial3.repository.entity.Booking;

import javax.swing.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    List<BookingResponse> toBookingResponseList(List<Booking> reservas);

    @Mapping(source = "usuario.username", target = "usuario")
    @Mapping(source = "evento.titulo", target = "evento")
    BookingResponse toBookingResponse(Booking reserva);

}
