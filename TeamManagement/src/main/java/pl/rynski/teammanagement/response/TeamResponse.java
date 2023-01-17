package pl.rynski.teammanagement.response;

import java.time.LocalDateTime;

import pl.rynski.teammanagement.model.Team;

public record TeamResponse(
		Integer id,
		String name,
		Integer userId,
		LocalDateTime creationDate) {
	
	public static TeamResponse toResponse(Team team) {
		return new TeamResponse(team.getId(), team.getName(), team.getCreatorId(), team.getCreationDate());
	}
}
