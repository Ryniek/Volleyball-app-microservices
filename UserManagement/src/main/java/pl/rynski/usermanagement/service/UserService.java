package pl.rynski.usermanagement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.rynski.usermanagement.model.User;
import pl.rynski.usermanagement.model.UserRole;
import pl.rynski.usermanagement.repository.UserRepository;
import pl.rynski.usermanagement.repository.UserRoleRepository;
import pl.rynski.usermanagement.request.CreateUserRequest;
import pl.rynski.usermanagement.response.UserResponse;

@Service
public record UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {

	public UserResponse createUser(CreateUserRequest userRequest) {
		UserRole userRole = userRoleRepository.findByName("ROLE_USER").get();
		User user = User.builder()
				.email(userRequest.email())
				.encryptedPassword(passwordEncoder.encode(userRequest.password()))
				.roles(List.of(userRole))
				.build();
		User savedUser = userRepository.save(user);
		return UserResponse.builder()
				.id(savedUser.getId())
				.email(savedUser.getEmail())
				.roles(savedUser.getRoles().stream().map(role -> role.getName()).toList())
				.build();
	}
	
	public UserResponse getUserDetailsByEmail(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		return UserResponse.builder()
				.id(user.getId())
				.email(user.getEmail())
				.roles(user.getRoles().stream().map(role -> role.getName()).toList())
				.build();
	}
}
