package pl.rynski.usermanagement.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.rynski.usermanagement.dto.UserDto;
import pl.rynski.usermanagement.request.CreateUserRequest;
import pl.rynski.usermanagement.request.LoginRequest;
import pl.rynski.usermanagement.response.CreateUserResponse;
import pl.rynski.usermanagement.service.UserService;

@RestController
@RequestMapping("/users")
public record UserController(UserService userService) {
	
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok().build();
    }

	@PostMapping
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest userRequest) {
		UserDto userDto = UserDto.builder()
				.email(userRequest.email())
				.password(userRequest.password())
				.build();
		UserDto response = userService.createUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new CreateUserResponse(response.getEmail(), response.getUserId()));
	}
}
