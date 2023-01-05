package pl.rynski.teammanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.rynski.teammanagement.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {

}
