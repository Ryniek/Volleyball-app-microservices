package pl.rynski.teammanagement.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.rynski.teammanagement.model.Team;
import pl.rynski.teammanagement.repository.TeamRepository;
import pl.rynski.teammanagement.request.CreateTeamRequest;
import pl.rynski.teammanagement.response.TeamResponse;
import pl.rynski.teammanagement.security.UserDetailsService;

@Service
@RequiredArgsConstructor
public class TeamService {
	
	private final TeamRepository teamRepository;
	private final UserDetailsService userDetailsService;
	
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
}
