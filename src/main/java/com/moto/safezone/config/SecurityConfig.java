package com.moto.safezone.config;

//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.context.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
//@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	String loginPage = "http://localhost:8080/oauth2/authorization/google";
	String frontIndexPage = "http://localhost:3001/";
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		    
			  http.cors()
			  .and()
			  .csrf().disable()
              .antMatcher("/**").authorizeRequests()
              .antMatchers(new String[]{"/", "/not-restricted"}).permitAll()
              .anyRequest().authenticated()
              .and().logout().logoutSuccessUrl("/").permitAll()
              .and()
              .oauth2Login().loginPage(loginPage)
              .defaultSuccessUrl(frontIndexPage);
			  
			  /**
			   * http
          .csrf().disable()
          .authorizeRequests()
          .antMatchers("/admin/**").hasRole("ADMIN")
          .antMatchers("/anonymous*").anonymous()
          .antMatchers("/login*").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/login.html")
          .loginProcessingUrl("/perform_login")
          .defaultSuccessUrl("/homepage.html", true)
          //.failureUrl("/login.html?error=true")
          .failureHandler(authenticationFailureHandler())
          .and()
          .logout()
          .logoutUrl("/perform_logout")
          .deleteCookies("JSESSIONID")
          .logoutSuccessHandler(logoutSuccessHandler());
			   */
			  
			  
//		        http
//		            .authorizeRequests(a -> a
//		                .antMatchers("/", "/error", "/webjars/**").permitAll()
//		                .anyRequest().authenticated()
//		            )
//		            .exceptionHandling(e -> e
//		                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//		            )
//		            .oauth2Login();   
		    }
		
		
		
//
//		http.httpBasic();
//		http.csrf().disable();
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//		http
//		.http.csrf().disable();
//		.antMatchers("/", "/login**")
//		.permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.logout()
//		.logoutUrl("/logout")
//		.logoutSuccessUrl("/")
//		.and().oauth2Login();
		
		
		// restricted all 
//		http
//		.authorizeRequests()
//		.http.csrf().disable();
//		.antMatchers("/", "/login**")
//		.authenticated()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.logout()
//		.logoutUrl("/logout")
//		.and().oauth2Login();
		
	

	
}
