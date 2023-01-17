package pl.rynski.teammanagement.security;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
	
	private final Environment environment;
	
	public MyCustomDsl(Environment environment) {
		this.environment = environment;
	}
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new AuthorizationFilter(authenticationManager, environment));
    }

    public static MyCustomDsl customDsl(Environment environment) {
        return new MyCustomDsl(environment);
    }
}

