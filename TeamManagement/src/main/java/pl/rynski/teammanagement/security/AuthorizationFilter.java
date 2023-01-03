package pl.rynski.teammanagement.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	private Environment environment;
	private final String headerString = "Authorization";
    private final String tokenPrefix = "Bearer ";

	public AuthorizationFilter(AuthenticationManager authManager, Environment environment) {
        super(authManager);
        this.environment = environment;
    }
	
	@Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = req.getHeader(headerString);

        if (authorizationHeader == null || !authorizationHeader.startsWith(tokenPrefix)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(authorizationHeader);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (token != null) {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(environment.getProperty("jwt.secret")))
                    .build()
                    .verify(token.replace(tokenPrefix, ""));
            String user = decodedJWT.getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, decodedJWT.getClaim("roles").asList(SimpleGrantedAuthority.class));
            }
        }
		return null;
    }
	
}
