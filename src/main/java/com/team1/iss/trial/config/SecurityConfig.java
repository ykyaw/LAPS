package com.team1.iss.trial.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.jdbcAuthentication().dataSource(dataSource)
//		.usersByUsernameQuery("select username, password, enabled from user where username = ?")
//		.authoritiesByUsernameQuery("select username, authorities from user where username = ?");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("SELECT t1.username, t1.password, t2.enabled FROM userlogin AS t1 INNER JOIN user AS t2 ON t1.username = t2.username where t1.username = ?")
		.authoritiesByUsernameQuery("select username, authorities from user where username = ?");
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeRequests() //This means we need to authorise all requests
		.antMatchers("/manager/**").hasRole("MANAGER") //This means for the manager API, allow only those with manager role. ** means anything after /manager/ also controlled
		.antMatchers("/admin/**").hasRole("ADMIN") //This means for the admin API, allow only those with ADMIN role. ** means anything after /admin/ also controlled
		.antMatchers("/employee/**").hasAnyRole("EMPLOYEE", "ADMIN", "MANAGER") // This means for users API, allow anyone with either ADMIN or USER role
		.antMatchers("/").permitAll() //This is the root, means at root level, permit everyone
		.and().formLogin().loginPage("/login")
		.and()
		.exceptionHandling().accessDeniedPage("/accessDenied")
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout-success").permitAll();
	}

	  
	@Bean
	public PasswordEncoder getPasswordEncoder() { return NoOpPasswordEncoder.getInstance();}
}


