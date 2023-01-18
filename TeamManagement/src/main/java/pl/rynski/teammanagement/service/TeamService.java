package pl.rynski.teammanagement.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.rynski.teammanagement.client.UserManagementClient;
import pl.rynski.teammanagement.exception.ResourceAlreadyExists;
import pl.rynski.teammanagement.exception.ResourceNotFoundException;
import pl.rynski.teammanagement.model.Team;
import pl.rynski.teammanagement.model.TeamInvitation;
import pl.rynski.teammanagement.repository.TeamInvitationRepository;
import pl.rynski.teammanagement.repository.TeamRepository;
import pl.rynski.teammanagement.request.CreateTeamRequest;
import pl.rynski.teammanagement.response.TeamResponse;
import pl.rynski.teammanagement.security.UserDetailsService;

@Service
@RequiredArgsConstructor
public class TeamService {
	
	private final TeamRepository teamRepository;
	private final UserDetailsService userDetailsService;
	private final UserManagementClient userManagementClient;
	private final TeamInvitationRepository teamInvitationRepository;
	
	public TeamResponse getTeamById(final Integer id) {
		
		return TeamResponse.toResponse(null);
	}

	@Transactional
	public TeamResponse createTeam(final CreateTeamRequest createTeamRequest) {
		final Team team = Team.builder()
				.name(createTeamRequest.name())
				.creationDate(LocalDateTime.now())
				.creatorId(userDetailsService.getLoggedUserId())
				.build();
		
		return TeamResponse.toResponse(teamRepository.save(team));
	}
	
	@Transactional
	public void inviteUser(final Integer teamId, final Integer userId) {
		//TODO chceck if user exists + if user is not already in the team
		Team team = teamRepository.findById(teamId).orElseThrow(() -> new ResourceNotFoundException("Team not found with given id"));
		if(!userManagementClient.checkIfUserExists(userId)) throw new ResourceNotFoundException("User not found with given id");
		if(team.getInvitations().stream().anyMatch(invitation -> invitation.getUserId().equals(userId))) throw new ResourceAlreadyExists("Invitation for given user already exists");
		
		teamInvitationRepository.save(TeamInvitation.builder()
				.team(team)
				.userId(userId)
				.sendingTime(LocalDateTime.now())
				.build());
	}
}
