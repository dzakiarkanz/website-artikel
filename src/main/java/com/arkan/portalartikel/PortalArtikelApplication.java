package com.arkan.portalartikel;

import com.arkan.portalartikel.model.User;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class PortalArtikelApplication {

    public static void main(String[] description) {
        SpringApplication.run(PortalArtikelApplication.class, description);
    }

    // Fungsi otomatis untuk menyuntikkan data akun ke Firebase saat aplikasi pertama kali menyala
    @Bean
    public CommandLineRunner initData(PasswordEncoder passwordEncoder) {
        return args -> {
            try {
                Firestore db = FirestoreClient.getFirestore();

                // 1. Cek & Buat Akun Admin otomatis jika belum ada di Firebase
                QuerySnapshot adminCheck = db.collection("users")
                        .whereEqualTo("username", "admin_utama")
                        .get().get();

                if (adminCheck.isEmpty()) {
                    Map<String, Object> adminData = new HashMap<>();
                    adminData.put("username", "admin_utama");
                    adminData.put("password", passwordEncoder.encode("admin123"));
                    adminData.put("role", "ROLE_ADMIN");

                    db.collection("users").add(adminData).get();
                    System.out.println(">>> [FIREBASE] Akun Admin otomatis berhasil dibuat: admin_utama / admin123");
                }

                // 2. Cek & Buat Akun Pembaca otomatis jika belum ada di Firebase
                QuerySnapshot pembacaCheck = db.collection("users")
                        .whereEqualTo("username", "ari_pembaca")
                        .get().get();

                if (pembacaCheck.isEmpty()) {
                    Map<String, Object> pembacaData = new HashMap<>();
                    pembacaData.put("username", "ari_pembaca");
                    pembacaData.put("password", passwordEncoder.encode("rahasia123"));
                    pembacaData.put("role", "ROLE_PEMBACA");

                    db.collection("users").add(pembacaData).get();
                    System.out.println(">>> [FIREBASE] Akun Pembaca otomatis berhasil dibuat: ari_pembaca / rahasia123");
                }

            } catch (Exception e) {
                System.err.println("Gagal menjalankan data seeder Firebase: " + e.getMessage());
            }
        };
    }
}