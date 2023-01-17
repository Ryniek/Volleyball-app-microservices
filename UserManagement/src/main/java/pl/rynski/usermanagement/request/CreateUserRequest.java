package pl.rynski.usermanagement.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record CreateUserRequest(
		@NotNull(message = "Email cannot be null")
		@Email
		@Size(max = 50, message = "Email maximum lenght is 130")
		String email,
		@NotNull(message = "Password cannot be null")
		@Size(min = 10, message = "Password minimum lenght is 10")
		@Size(max = 100, message = "Password maximum lenght is 100")
		String password,
		@NotNull(message = "First name cannot be null")
		@Size(min = 2, message = "First name minimum lenght is 2")
		@Size(max = 50, message = "First name maximum lenght is 50")
		String firstName,
		@NotNull(message = "Last name cannot be null")
		@Size(min = 2, message = "Last name minimum lenght is 2")
		@Size(max = 50, message = "Last name maximum lenght is 50")
		String lastName,
		@Min(value = 200, message = "Attack jump minimum value is 200")
		@Max(value = 400, message = "Attack jump maximum value is 400")
		Integer attackJump,
		@Min(value = 200, message = "Block jump minimum value is 200")
		@Max(value = 400, message = "Block jump maximum value is 400")
		Integer blockJump
		) {
}
