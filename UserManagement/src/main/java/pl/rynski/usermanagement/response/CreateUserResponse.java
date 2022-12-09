package pl.rynski.usermanagement.response;

public record CreateUserResponse(
		String email,
		String userId
) {
}
