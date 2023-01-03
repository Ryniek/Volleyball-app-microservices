package pl.rynski.usermanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import pl.rynski.usermanagement.dto.UserDto;
import pl.rynski.usermanagement.request.CreateUserRequest;
import pl.rynski.usermanagement.request.LoginRequest;
import pl.rynski.usermanagement.response.CreateUserResponse;
import pl.rynski.usermanagement.security.CustomUserDetailsService;
import pl.rynski.usermanagement.security.JwtTokenGenerator;
import pl.rynski.usermanagement.service.UserService;

@RestController
@RequestMapping("/users")
public record UserController(UserService userService, Environment environment, CustomUserDetailsService customUserDetailsService) {

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/token/refresh")
	public ResponseEntity<?> refreshToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				Algorithm algorithm = Algorithm.HMAC256(environment.getProperty("jwt.token.secret"));
				Algorithm refreshAlgorithm = Algorithm.HMAC256(environment.getProperty("jwt.refresh.secret"));
				DecodedJWT decodedJWT = JwtTokenGenerator.getDecodedJwt(authorizationHeader, refreshAlgorithm);
				String username = decodedJWT.getSubject();
				UserDto user = userService.getUserDetailsByEmail(username);
				return ResponseEntity.status(HttpStatus.CREATED).body(JwtTokenGenerator.generateTokens(user, environment, algorithm, refreshAlgorithm));
			} catch (Exception exception) {
		        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Refresh token is missing");
		}
	}

	@GetMapping("/test")
	public ResponseEntity<?> test(HttpServletRequest request) {
		System.out.println(environment.getProperty("jwt.token.secret"));
		System.out.println(request.getHeader("Authorization"));
		System.out.println(customUserDetailsService.getLoggedUser().getEmail());
		return ResponseEntity.ok().build();
	}

	@PostMapping
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest userRequest) {
		UserDto userDto = UserDto.builder().email(userRequest.email()).password(userRequest.password()).build();
		UserDto response = userService.createUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new CreateUserResponse(response.getEmail(), response.getUserId()));
	}
}
