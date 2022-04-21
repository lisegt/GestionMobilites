package fr.jfc.ptut.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.jfc.ptut.security.jwt.AuthEntryPointJwt;
import fr.jfc.ptut.security.jwt.AuthTokenFilter;
import fr.jfc.ptut.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    
    
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    
    /**
     * 
     * @return AuthTokenFilter
     */
    @Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

    /**
     * Méthode de configuration pour l'encodage du mot de passe
     */
    @Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

    /**
     * 
     * @return la méthode d'encryptage du mot de passe
     */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    /**
     * ???
     */
    @Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and() //Pour que spring ne renvoie pas une page de connection par defaut
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //Configuration en session stateless
            .authorizeRequests().antMatchers("/api/auth/**").permitAll() //On definit les requêtes autorisés (Authentification)
			.antMatchers("/api/test/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/destinations/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/mobilites/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/documents/**").permitAll()
			.anyRequest().authenticated(); //Toutes les autres requêtes doivent être authentifiées
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
    

}
