package pl.rynski.teammanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.rynski.teammanagement.model.TeamInvitation;

public interface TeamInvitationRepository extends JpaRepository<TeamInvitation, Integer> {

}
