package com.sistema.delivery.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // habilitar métodos somente com perfil pré-estabelecido
public class SecurityWeb extends WebSecurityConfigurerAdapter{

    //Usando para habilitar h2 - sem ele não deixa acessa o banco
    @Autowired
    private Environment environment;
    @Autowired
    private UserDetailsService detailsService;
    @Autowired
    private JWTUtil jwtUtil;

    private static final String[] PUBLIC_MATCHRES = {
            "/h2-console/**"
    };

    private static final String[] PUBLIC_MATCHRES_GET = {
        "/produtos/**",
        "/categorias/**",
    };

    //permitir cadastro de clientes
    private static final String[] PUBLIC_MATCHRES_POST = {
        "/clientes/**",
        "/auth/senha/**",

    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // habilitar uso h2 no navegador
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();            
        }

        http.cors()                                      //configuração para habilitar cors mas na classe terá uma congiração
            .and().csrf().disable()                     // como não termeos sessão pode desabilitar csrf
            .authorizeRequests()     
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHRES_GET).permitAll()   
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHRES_POST).permitAll()                 // iniciar as autorizações
                .antMatchers(PUBLIC_MATCHRES).permitAll()                
                .anyRequest().authenticated()
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilter(new JWTAutenticacaoFilter(authenticationManager(), jwtUtil))
                .addFilter(new JTWAutorizacaoFilter(authenticationManager(), jwtUtil, detailsService));

    }

    @Bean
    CorsConfigurationSource configurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
}
