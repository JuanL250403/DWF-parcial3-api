package sv.edu.udb.parcial3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.parcial3.repository.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);
}
