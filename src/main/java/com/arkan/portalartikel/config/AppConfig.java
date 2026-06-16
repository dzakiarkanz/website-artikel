package com.arkan.portalartikel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Enkripsi standar yang aman dan kompatibel dengan kode seeder & registrasi Anda
        return new BCryptPasswordEncoder();
    }
}