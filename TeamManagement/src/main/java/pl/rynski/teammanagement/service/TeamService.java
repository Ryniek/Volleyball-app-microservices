package pl.rynski.teammanagement.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import pl.rynski.teammanagement.model.Team;
import pl.rynski.teammanagement.repository.TeamRepository;
import pl.rynski.teammanagement.request.CreateTeamRequest;
import pl.rynski.teammanagement.response.CreateTeamResponse;

@Service
public record TeamService(TeamRepository teamRepository) {

	public CreateTeamResponse createTeam(CreateTeamRequest createTeamRequest, HttpServletRequest request) {
		Team team = Team.builder()
				.name(createTeamRequest.name()).build();
		team.setCreatorId(request.getHeader("userId"));
		Team savedUser = teamRepository.save(team);
		return new CreateTeamResponse(savedUser.getId(), savedUser.getName(), savedUser.getCreatorId());
	}
}
