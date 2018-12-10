package com.bookstore.Bookstore;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bookstore.Bookstore.web.UserDetailServiceImpl;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/css/**").permitAll()
		.and()
		.authorizeRequests().antMatchers("/h2-console/**").permitAll()
		.and()
		.authorizeRequests().antMatchers("/signup", "/saveuser").permitAll()
        .and()
		.authorizeRequests().anyRequest().authenticated()
		.and()
	.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/booklist")
		.permitAll()
		.and()
	.logout()
		.permitAll();
	}
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}

	/** 
	 * In-Memory data
	 * 
	 * @Bean 
		@Override
	public UserDetailsService userDetailsService() {
		List<UserDetails> users = new ArrayList();
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("pass")
				.roles("USER")
				.build();
		
		users.add(user);
		
		user = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("passu")
				.roles("USER", "ADMIN")
				.build();
		
		users.add(user);
		
		return new InMemoryUserDetailsManager(users);
	}
	**/

