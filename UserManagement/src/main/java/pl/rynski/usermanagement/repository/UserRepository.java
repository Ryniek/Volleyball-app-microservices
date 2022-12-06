package pl.rynski.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.rynski.usermanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
