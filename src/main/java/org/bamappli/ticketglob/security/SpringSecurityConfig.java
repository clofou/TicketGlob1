package org.bamappli.ticketglob.security;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
    UserDetailsServiceImpl userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.userDetailsService(userDetailsService);
        return http.csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/admin/**").hasRole("ADMIN");
                    auth.requestMatchers("/admin/apprenants").hasRole("FORMATEUR");
                    auth.requestMatchers("/apprenant/**").hasRole("APPRENANT");
                    auth.requestMatchers("/formateur/**").hasRole("FORMATEUR");
                }).httpBasic(Customizer.withDefaults()).build();
    }

    //@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public UserDetailsService users(){

        UserDetails user = User.builder().
                username("apprenant").
                password(passwordEncoder().encode("apprenant")).
                roles("APPRENANT").build();
        UserDetails admin = User.builder().
                username("admin").
                password(passwordEncoder().encode("admin")).
                roles("FORMATEUR", "ADMIN", "APPRENANT").build();
        UserDetails formateur = User.builder().
                username("alou").
                password(passwordEncoder().encode("alou")).
                roles("FORMATEUR", "APPRENANT").build();

        return new InMemoryUserDetailsManager(user, admin, formateur);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(users());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}


