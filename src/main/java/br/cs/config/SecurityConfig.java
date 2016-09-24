package br.cs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	protected void configure(HttpSecurity http) throws Exception {
		((HttpSecurity) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) http.authorizeRequests().antMatchers(new String[] { "/site/**" }))
				.access("hasRole('ROLE_ADMIN')").and()).formLogin().loginPage("/login");
		http.csrf().disable();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.authenticator());
		super.configure(auth);
	}

	@Bean
	public AuthenticationProvider authenticator() {
		return new CustomAuthenticationProvider();
	}
}
