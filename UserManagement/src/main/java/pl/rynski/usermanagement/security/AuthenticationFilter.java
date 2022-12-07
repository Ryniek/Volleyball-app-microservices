package pl.rynski.usermanagement.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.rynski.usermanagement.request.LoginRequest;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
    private AuthenticationManager authenticationManager;
    
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/users/login");
    }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			LoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.email(), creds.password(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {

        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        res.setContentType("application/json");
        res.getWriter().write(new ObjectMapper().writeValueAsString(principal.getUsername()));
        res.getWriter().flush();
    }
}
