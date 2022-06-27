package rest_exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_exam.models.entitis.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}