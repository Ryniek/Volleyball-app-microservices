package pl.rynski.usermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.rynski.usermanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	//TODO sprawdzic ile zapytan sie wykonuje i jesli wiecej niz jedno to napisac wlasne query
	Optional<User> findByEmail(String email);
}
