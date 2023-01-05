package pl.rynski.usermanagement.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import pl.rynski.usermanagement.response.UserResponse;

public class JwtTokenGenerator {
	
	public static DecodedJWT getDecodedJwt(String authorizationHeader, Algorithm algorithm) {
		String refreshToken = authorizationHeader.substring("Bearer ".length());
		JWTVerifier verifier = JWT.require(algorithm).build();
		return verifier.verify(refreshToken);
	}

	public static Map<String, String> generateTokens(UserResponse user, Environment environment, Algorithm algorithm, Algorithm refreshAlgorithm) {
		String access_token = JWT.create()
                .withSubject(user.email())
                .withClaim("userId", user.id())
                .withClaim("roles", user.roles())
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("jwt.access.token.validity"))))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.email())
                .withClaim("userId", user.id())
                .withClaim("roles", user.roles())
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("jwt.refresh.token.validity"))))
                .sign(refreshAlgorithm);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        return tokens;
	}
}
