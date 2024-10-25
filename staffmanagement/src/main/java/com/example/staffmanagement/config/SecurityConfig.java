package com.example.staffmanagement.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection for simplicity (not recommended for production)
                .authorizeRequests(auth -> auth
                        .requestMatchers("/api/v1/manager/**").permitAll()  // Allow these endpoints without authentication
                        .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .formLogin(formLogin -> formLogin.disable());
//                        .loginPage("/login")  // Specify custom login page (create a login.html)
//                        .defaultSuccessUrl("/home", true)  // Redirect to this page after successful login
//                        .permitAll()  // Allow everyone to see the login page
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll()
//                );

        return http.build();
    }

    public void configure(WebSecurity web) throws Exception {
        // Exclude static resources from security
        web.ignoring().requestMatchers("/resources/**", "/static/**", "/public/**");
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
