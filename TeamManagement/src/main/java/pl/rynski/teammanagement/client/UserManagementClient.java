package pl.rynski.teammanagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//TODO circuit breaker
@FeignClient(name = "user-management")
public interface UserManagementClient {

	@GetMapping("/api/v1/users/exists/{userId}")
	Boolean checkIfUserExists(@PathVariable(name = "userId") final Integer userId);
}
