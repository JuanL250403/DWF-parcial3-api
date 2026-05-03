package sv.edu.udb.parcial3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.parcial3.repository.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
