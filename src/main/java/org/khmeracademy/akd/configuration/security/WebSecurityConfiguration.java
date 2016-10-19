package org.khmeracademy.akd.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.couchbase.client.core.utils.Base64;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		//Authentication: Basic QURNSU5fQVBJOkFETUlOX1BAU1NXT1JE
		/*auth.inMemoryAuthentication()
				.withUser("ADMIN_API").password("ADMIN_P@SSWORD").roles("ADMIN_API");*/
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**").permitAll();
		
		http.csrf().disable();
	/*	http.httpBasic().authenticationEntryPoint(new RESTAuthenticationEntryPoint());
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
		
	}
	
	/*public static void main(String args[]){
		System.out.println(Base64.encode("ADMIN_API:ADMIN_P@SSWORD".getBytes()));
	}*/
}
