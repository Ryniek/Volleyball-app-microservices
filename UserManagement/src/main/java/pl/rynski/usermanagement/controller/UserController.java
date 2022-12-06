package pl.rynski.usermanagement.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.rynski.usermanagement.request.CreateUserRequest;

@RestController
@RequestMapping("/users")
public record UserController() {

	@PostMapping
	public String createUser(@RequestBody @Valid CreateUserRequest userRequest) {
		return null;
	}
}
