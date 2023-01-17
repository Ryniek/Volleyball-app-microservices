package pl.rynski.usermanagement.response;

import java.util.List;

import lombok.Builder;
import pl.rynski.usermanagement.model.User;

public record UserResponse(
		Integer id,
		String email,
		String firstName,
		String lastName,
		Integer attackJump,
		Integer blockJump,
		List<String> roles
) {
	
	public static UserResponse toResponse(User user) {
		return new UserResponse(user.getId(), 
				user.getEmail(), 
				user.getFirstName(), 
				user.getLastName(), 
				user.getAttackJump(), 
				user.getBlockJump(), 
				user.getRoles().stream().map(role -> role.getName()).toList());
	}
}
