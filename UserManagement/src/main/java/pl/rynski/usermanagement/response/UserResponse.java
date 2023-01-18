package pl.rynski.usermanagement.response;

import java.util.List;

import pl.rynski.usermanagement.model.Position;
import pl.rynski.usermanagement.model.User;

public record UserResponse(
		Integer id,
		String email,
		String firstName,
		String lastName,
		Position position,
		Integer attackJump,
		Integer blockJump,
		List<String> roles
) {
	
	public static UserResponse toResponse(User user) {
		return new UserResponse(user.getId(), 
				user.getEmail(), 
				user.getFirstName(), 
				user.getLastName(), 
				user.getPosition(),
				user.getAttackJump(), 
				user.getBlockJump(), 
				user.getRoles().stream().map(role -> role.getName()).toList());
	}
}
