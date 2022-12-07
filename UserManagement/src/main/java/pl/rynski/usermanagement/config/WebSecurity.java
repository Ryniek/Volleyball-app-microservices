package pl.rynski.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import pl.rynski.usermanagement.security.CustomUserDetailsService;
import pl.rynski.usermanagement.security.MyCustomDsl;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity {

	private final CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.apply(MyCustomDsl.customDsl()).and()
		.csrf().disable()
		.authorizeHttpRequests((autz) -> autz
				.antMatchers("/users/login", "/users").permitAll()
				.anyRequest().authenticated())
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
			      .userDetailsService(customUserDetailsService)
			      .passwordEncoder(getBCryptPasswordEncoder())
			      .and()
			      .build();
	}

	@Bean
	public PasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
