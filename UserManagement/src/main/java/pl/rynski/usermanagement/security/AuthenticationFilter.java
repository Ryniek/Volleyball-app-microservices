package pl.rynski.usermanagement.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.rynski.usermanagement.request.LoginRequest;
import pl.rynski.usermanagement.response.UserResponse;
import pl.rynski.usermanagement.service.UserService;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final Environment environment;
    
    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Environment environment) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.environment = environment;
        setFilterProcessesUrl("/api/v1/users/login");
    }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			final LoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.email(), 
							creds.password(), 
							new ArrayList<>()
							));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {

        final UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		final Algorithm algorithm = Algorithm.HMAC256(environment.getProperty("jwt.token.secret"));
		final Algorithm refreshAlgorithm = Algorithm.HMAC256(environment.getProperty("jwt.refresh.secret"));
        final UserResponse user = userService.getUserDetailsByEmail(principal.getUsername());
        res.setContentType("application/json");
        new ObjectMapper().writeValue(
        			res.getOutputStream(), 
        			JwtTokenGenerator.generateTokens(user, environment, algorithm, refreshAlgorithm)
        			);
    }
	
	
}
