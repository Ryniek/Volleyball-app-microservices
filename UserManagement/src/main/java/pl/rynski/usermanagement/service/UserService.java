package pl.rynski.usermanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.rynski.usermanagement.model.User;
import pl.rynski.usermanagement.model.UserRole;
import pl.rynski.usermanagement.repository.UserRepository;
import pl.rynski.usermanagement.repository.UserRoleRepository;
import pl.rynski.usermanagement.request.CreateUserRequest;
import pl.rynski.usermanagement.response.UserResponse;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public UserResponse createUser(final CreateUserRequest userRequest) {
		final UserRole userRole = userRoleRepository.findByName("ROLE_USER").get();
		final User user = User.builder()
				.email(userRequest.email())
				.encryptedPassword(passwordEncoder.encode(userRequest.password()))
				.firstName(userRequest.firstName())
				.lastName(userRequest.lastName())
				.attackJump(userRequest.attackJump())
				.blockJump(userRequest.blockJump())
				.roles(List.of(userRole))
				.build();
		
		return UserResponse.toResponse(userRepository.save(user));
	}
	
	public UserResponse getUserDetailsByEmail(final String email) {
		final User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		return UserResponse.toResponse(user);
	}
}
