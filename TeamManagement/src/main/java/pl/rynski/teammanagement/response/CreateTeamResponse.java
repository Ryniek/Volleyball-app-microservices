package pl.rynski.teammanagement.response;

public record CreateTeamResponse(
		Long id,
		String name,
		String userId) {
}
