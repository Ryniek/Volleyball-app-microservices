package pl.rynski.usermanagement.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.rynski.usermanagement.model.User;
import pl.rynski.usermanagement.repository.UserRepository;

@Service
public record CustomUserDetailsService(UserRepository userRepository) implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository
				.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		return UserPrincipal.create(user);
	}

	public User getLoggedUser() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userRepository
                .findByEmail(currentUserEmail)
                .get(); //TODO Exception handling
    }
}
