package pl.rynski.teammanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {
	
	private final Environment environment;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.apply(MyCustomDsl.customDsl(environment)).and()
		.csrf().disable()
		.authorizeHttpRequests((autz) -> autz
				.anyRequest().permitAll())
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
}
