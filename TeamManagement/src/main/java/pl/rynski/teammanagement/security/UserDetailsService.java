package pl.rynski.teammanagement.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public record UserDetailsService() {
	
	public Integer getLoggedUserId() {
        return Integer.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}
