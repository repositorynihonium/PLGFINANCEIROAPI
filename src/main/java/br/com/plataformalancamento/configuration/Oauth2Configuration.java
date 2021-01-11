//package br.com.plataformalancamento.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//@EnableResourceServer
//public class Oauth2Configuration extends ResourceServerConfigurerAdapter {
//	
//	@Autowired
//	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.inMemoryAuthentication().withUser("ADMINISTRADOR_FINANCEIRO").password("1234").roles("ROLE");
//	}
//	
//	@Override
//	public void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity
//			.authorizeRequests()
//			.antMatchers("/categoria-lancamento-financeiro")
//			.permitAll()
//			.anyRequest()
//			.authenticated()
//			.and()
//			.httpBasic()
//			.and()
//			.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//			.csrf()
//			.disable();
//	}
//	
//	@Override
//	public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) throws Exception {
//		resourceServerSecurityConfigurer.stateless(true);
//	}
//	
//}
