package sv.edu.udb.parcial3.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sv.edu.udb.parcial3.controller.request.EventRequest;
import sv.edu.udb.parcial3.controller.response.EventResponse;
import sv.edu.udb.parcial3.repository.entity.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    List<EventResponse> toEventResponseList(List<Event> eventos);

    EventResponse toEventResponse(Event event);

    Event toEvent(EventRequest evento);
}
