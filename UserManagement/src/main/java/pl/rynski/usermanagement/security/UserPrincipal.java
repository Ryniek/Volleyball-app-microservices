package pl.rynski.usermanagement.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import pl.rynski.usermanagement.model.User;

public class UserPrincipal implements UserDetails {
	
	private final Integer id;
	private final String email;
    private final String password;
    
	public UserPrincipal(Integer id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
    public static UserPrincipal create(User user) {
        return new UserPrincipal(user.getId(), user.getEmail(), user.getEncryptedPassword());
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
