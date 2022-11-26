package edu.sjsu.cmpe275.nft.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// This class contains security configuration for authorizing requests to URL's, password encryption bean (for password encryption) and authentication manager bean (for user authentication)
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// authorizing requests for certain end points
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests().antMatchers("/", "/index.html", "/registerUser", "/localLogin").permitAll()
				.anyRequest().authenticated().and().csrf().disable().oauth2Login().defaultSuccessUrl("/profile").failureUrl("/index.html").and()
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true));
	}
}
