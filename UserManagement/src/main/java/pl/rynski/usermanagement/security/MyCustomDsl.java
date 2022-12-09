package pl.rynski.usermanagement.security;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import pl.rynski.usermanagement.service.UserService;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
	
	private UserService userService;
	private Environment environment;
	
	public MyCustomDsl(UserService userService, Environment environment) {
		this.userService = userService;
		this.environment = environment;
	}
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new AuthenticationFilter(authenticationManager, userService, environment));
    }

    public static MyCustomDsl customDsl(UserService userService, Environment environment) {
        return new MyCustomDsl(userService, environment);
    }
}
