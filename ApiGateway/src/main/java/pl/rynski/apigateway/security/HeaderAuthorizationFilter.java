package pl.rynski.apigateway.security;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HeaderAuthorizationFilter extends AbstractGatewayFilterFactory<HeaderAuthorizationFilter.Config>{

	@Autowired
	private Environment environment;
	
	public HeaderAuthorizationFilter() {
		super(Config.class);
	}
	
	public static class Config {
		
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				return onError(exchange, "Authorization header not passed", HttpStatus.FORBIDDEN);
			}
			String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			String accessToken = authorizationHeader.substring("Bearer ".length());
			try {
				Algorithm algorithm = Algorithm.HMAC256(environment.getProperty("jwt.token.secret"));
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(accessToken);
				String username = decodedJWT.getSubject();
				
//				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//						username, null, Collections.emptyList());
//				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//				filterChain.doFilter(request, response);
			} catch (Exception exception) {
		        return onError(exchange, exception.getMessage(), HttpStatus.FORBIDDEN);
			}
			return chain.filter(exchange);
		};
	}
	
	private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
	    byte[] bytes = error.getBytes(StandardCharsets.UTF_8);
	    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
		response.writeWith(Flux.just(buffer));
		
		return response.setComplete();
	}

}
