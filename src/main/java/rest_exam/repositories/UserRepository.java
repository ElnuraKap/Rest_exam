package rest_exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_exam.models.entitis.User;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
