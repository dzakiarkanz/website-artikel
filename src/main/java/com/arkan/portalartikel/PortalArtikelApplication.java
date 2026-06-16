package com.arkan.portalartikel;

import com.arkan.portalartikel.model.User;
import com.arkan.portalartikel.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PortalArtikelApplication {

    public static void main(String[] description) {
        SpringApplication.run(PortalArtikelApplication.class, description);
    }

    // Fungsi otomatis untuk menyuntikkan data akun saat aplikasi pertama kali menyala
    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // 1. Cek & Buat Akun Admin otomatis jika belum ada di database
            if (userRepository.findByUsername("admin_utama").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin_utama");
                // Password dienkripsi dulu sebelum masuk database
                admin.setPassword(passwordEncoder.encode("admin123")); 
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
                System.out.println(">>> [DATABASE] Akun Admin otomatis berhasil dibuat: admin_utama / admin123");
            }

            // 2. Cek & Buat Akun Pembaca contoh otomatis jika belum ada
            if (userRepository.findByUsername("ari_pembaca").isEmpty()) {
                User pembaca = new User();
                pembaca.setUsername("ari_pembaca");
                pembaca.setPassword(passwordEncoder.encode("rahasia123"));
                pembaca.setRole("ROLE_PEMBACA");
                userRepository.save(pembaca);
                System.out.println(">>> [DATABASE] Akun Pembaca otomatis berhasil dibuat: ari_pembaca / rahasia123");
            }
        };
    }
}