package pl.rynski.teammanagement.service;

import org.springframework.stereotype.Service;

import pl.rynski.teammanagement.model.Team;
import pl.rynski.teammanagement.repository.TeamRepository;
import pl.rynski.teammanagement.request.CreateTeamRequest;
import pl.rynski.teammanagement.response.CreateTeamResponse;
import pl.rynski.teammanagement.security.UserDetailsService;

@Service
public record TeamService(TeamRepository teamRepository, UserDetailsService userDetailsService) {

	public CreateTeamResponse createTeam(CreateTeamRequest createTeamRequest) {
		Team team = Team.builder()
				.name(createTeamRequest.name()).build();
		team.setCreatorId(userDetailsService.getLoggedUserId());
		Team savedUser = teamRepository.save(team);
		return new CreateTeamResponse(savedUser.getId(), savedUser.getName(), savedUser.getCreatorId());
	}
}
