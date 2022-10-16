package com.example.demoauth.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demoauth.configs.jwt.AuthEntryPointJwt;
import com.example.demoauth.configs.jwt.AuthTokenFilter;
import com.example.demoauth.configs.jwt.CustomAuthenticationSuccessHandler;
import com.example.demoauth.models.User;
import com.example.demoauth.service.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationsuccessHandler;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("//**");
	}
	
	@Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(datasource);
    }
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	 @Bean
	   public DaoAuthenticationProvider authenticationProvider() {
	      
	      DaoAuthenticationProvider auth = 
	            new DaoAuthenticationProvider();
	      auth.setUserDetailsService(userDetailsService);
	      auth.setPasswordEncoder(passwordEncoder());
	      return auth;
	   }
	 
//	 @Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.authorizeRequests().antMatchers("/api/auth/**").permitAll().antMatchers("/welcome").hasAnyRole("USER", "ADMIN")
//					.antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN").antMatchers("/addNewEmployee")
//					.hasAnyRole("ADMIN").anyRequest().authenticated()
//					.and().formLogin().loginPage("/api/auth/signin").permitAll().and().logout().permitAll();
//
//			http.csrf().disable();
//		}
//	 @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//
//	        http.csrf().disable()
//	                .authorizeRequests()
//						.antMatchers("/", "/home", "/about").permitAll()
//						.antMatchers("/admin/**").hasAnyRole("ADMIN")
//						.antMatchers("/user/**").hasAnyRole("USER")
//						.anyRequest().authenticated()
//	                .and()
//	                .formLogin()
//						.loginPage("/login")
//						.permitAll();
//						
//	    }

	    // create two users, admin and user
//	    @Autowired
//	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//	        auth.inMemoryAuthentication()
//	                .withUser("user").password("password").roles("USER")
//	                .and()
//	                .withUser("admin").password("password").roles("ADMIN");
//	    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/api/test/**").permitAll()
				.antMatchers("/api/employee/data/**").permitAll()
				.antMatchers("/api/employee/schedule/**").permitAll()
				.antMatchers("/api/employee/**").permitAll()
				.antMatchers("/api/employee/data/assignment/**").permitAll()
				.antMatchers("/api/user/delete/**").permitAll()
				.anyRequest().authenticated()
				;
				
		
	//	http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		
	   

//		http
//		.authorizeRequests(authorizeRequests -> 
//				authorizeRequests
//					.antMatchers("/board/*").hasAnyRole("MANAGER", "OPERATOR")
//					.antMatchers("/members/*").hasRole("MANAGER")
//					.antMatchers("/").permitAll())
//		.httpBasic().realmName("org team")
//		.and()
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
	
//	@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("admin").password("password").roles("ADMIN");
//    }
	
	

