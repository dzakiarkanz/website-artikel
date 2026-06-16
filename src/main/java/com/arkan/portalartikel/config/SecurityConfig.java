package com.arkan.portalartikel.config;

import com.arkan.portalartikel.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Pengaturan Hak Akses URL (Otorisasi)
            .authorizeHttpRequests(auth -> auth
                // URL yang bebas diakses siapa saja tanpa login
                .requestMatchers("/", "/article/**", "/register", "/login", "/css/**", "/js/**").permitAll()
                
                // URL dashboard admin hanya bisa diakses oleh pengguna dengan ROLE_ADMIN
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                
                // URL untuk simpan komentar harus login terlebih dahulu (bisa ADMIN atau PEMBACA)
                .requestMatchers("/article/*/comment").authenticated()
                
                // Sisa URL lainnya wajib login
                .anyRequest().authenticated()
            )
            // Pengaturan Form Login Custom
            .formLogin(form -> form
                .loginPage("/login") // Mengarah ke URL login buatan kita sendiri
                .loginProcessingUrl("/login") // URL internal Spring Security untuk memproses form
                .defaultSuccessUrl("/", true) // Jika login sukses, dilempar ke beranda utama
                .permitAll()
            )
            // Pengaturan Logout
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // Setelah logout, balikkan ke halaman login
                .permitAll()
            )
            .userDetailsService(userDetailsService);

        return http.build();
    }

    // Bean untuk mengenkripsi password dengan algoritma BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}