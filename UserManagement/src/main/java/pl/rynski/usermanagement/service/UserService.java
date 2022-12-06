package pl.rynski.usermanagement.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import pl.rynski.usermanagement.dto.UserDto;

@Service
public record UserService() {

	public UserDto createUser(UserDto userDto) {
		
		userDto.setUserId(UUID.randomUUID().toString());
		return null;
	}
}
