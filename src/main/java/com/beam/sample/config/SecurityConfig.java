package com.beam.sample.config;

import com.beam.sample.model.mongo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	private ShaPasswordEncoder passwordEncoder;
	

	@Autowired
	public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() {
		try {
			return super.authenticationManagerBean();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setSaltSource(new SaltSource() {
            public Object getSalt(UserDetails userDetails) {
                User user = (User) userDetails;
                return user.getUsername();
            }
        });

		daoAuthenticationProvider.setUserDetailsService(userDetailsService);

		return daoAuthenticationProvider;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/3rd/**", "/resources/app/**", "/evidences/**", "/api/v1.1/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**")
					.permitAll()
				.antMatchers("/*")
					.permitAll()
				.anyRequest()
					.authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
			.and()
				.logout()
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.permitAll()
			.and()
				.headers()
					.defaultsDisabled()
					.frameOptions()
						.sameOrigin()
					.cacheControl();
        //@formatter:on
    }

}
