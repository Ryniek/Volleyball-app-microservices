package pl.rynski.usermanagement.response;

import java.util.List;

import lombok.Builder;

@Builder
public record UserResponse(
		Integer id,
		String email,
		List<String> roles
) {
}
