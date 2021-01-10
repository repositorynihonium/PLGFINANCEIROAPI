package br.com.plataformalancamento.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SegurancaConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.inMemoryAuthentication().withUser("ADMINISTRADOR_FINANCEIRO").password("1234").roles("ROLE");
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeRequests()
			.antMatchers("/categoria-lancamento-financeiro")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.csrf()
			.disable();
	}
	
}
