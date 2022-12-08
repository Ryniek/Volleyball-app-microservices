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

import pl.rynski.usermanagement.dto.UserDto;
import pl.rynski.usermanagement.request.LoginRequest;
import pl.rynski.usermanagement.service.UserService;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private Environment environment;
    
    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, Environment environment) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.environment = environment;
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
		Algorithm algorithm = Algorithm.HMAC256(environment.getProperty("jwt.token.secret"));
		Algorithm refreshAlgorithm = Algorithm.HMAC256(environment.getProperty("jwt.refresh.secret"));
        UserDto user = userService.getUserDetailsByEmail(principal.getUsername());
        res.setContentType("application/json");
        new ObjectMapper().writeValue(
        			res.getOutputStream(), 
        			JwtTokenGenerator.generateTokens(user, environment, algorithm, refreshAlgorithm)
        			);
    }
	
	
}
