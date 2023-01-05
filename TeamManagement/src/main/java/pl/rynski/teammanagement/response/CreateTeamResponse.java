package pl.rynski.teammanagement.response;

public record CreateTeamResponse(
		Integer id,
		String name,
		Integer userId) {
}
