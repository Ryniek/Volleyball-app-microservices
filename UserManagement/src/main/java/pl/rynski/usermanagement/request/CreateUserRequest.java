package pl.rynski.usermanagement.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record CreateUserRequest(
		@NotNull(message = "Email cannot be null")
		@Email
		@Size(max = 50, message = "Email maximum lenght is 50")
		String email,
		@NotNull(message = "Password cannot be null")
		@Size(min = 10, message = "Password minimum lenght is 10")
		@Size(max = 100, message = "Password maximum lenght is 100")
		String password
		) {

}
