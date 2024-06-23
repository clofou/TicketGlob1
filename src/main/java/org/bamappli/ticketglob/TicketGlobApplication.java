package org.bamappli.ticketglob;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;


@SpringBootApplication
public class TicketGlobApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketGlobApplication.class, args);

    }

    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager, BCryptPasswordEncoder passwordEncoder) {

        return args -> {
            UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("admin1");
            if (u1 ==null)
                jdbcUserDetailsManager.createUser(User.withUsername("admin1").password(passwordEncoder.encode("1234")).roles("ADMIN").build());

            UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user1");
            if(u2==null)
                jdbcUserDetailsManager.createUser(User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build());
        };
    }

}
