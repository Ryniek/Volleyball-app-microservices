package pl.rynski.usermanagement.request;

public record LoginRequest(
		String email,
		String password
		) {
}
