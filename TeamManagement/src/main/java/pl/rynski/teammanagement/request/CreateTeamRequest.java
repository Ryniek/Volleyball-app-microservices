package pl.rynski.teammanagement.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record CreateTeamRequest(
		@NotNull(message = "Team name cannot be null")
		@Size(min = 3, message = "Minimum lenght of team name is 3")
		@Size(max = 100, message = "Maximum lenght of team name is 100")
		String name
		) {

}
