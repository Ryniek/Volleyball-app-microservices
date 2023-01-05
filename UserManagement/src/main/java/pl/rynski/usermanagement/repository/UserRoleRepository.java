package pl.rynski.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.rynski.usermanagement.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	Optional<UserRole> findByName(String name);
}
