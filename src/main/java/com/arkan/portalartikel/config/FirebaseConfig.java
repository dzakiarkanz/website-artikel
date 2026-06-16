package com.arkan.portalartikel.config;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initializeFirebase() {
        try {
            // Membaca file JSON dari folder resources secara dinamis
            InputStream serviceAccount = getClass().getClassLoader()
                    .getResourceAsStream("website-artikel-d642a-firebase-adminsdk-fbsvc-8c1eb764b6.json");

            if (serviceAccount == null) {
                throw new RuntimeException("File kredensial Firebase tidak ditemukan di folder resources!");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    // Ganti URL di bawah jika Anda menggunakan Realtime Database, 
                    // jika hanya menggunakan Firestore / Auth, baris databaseUrl bisa dihapus
                    .setDatabaseUrl("https://website-artikel-d642a-default-rtdb.firebaseio.com") 
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("=================================================");
                System.out.println(" KONEKSI FIREBASE BERHASIL DIINISIALISASI! ");
                System.out.println("=================================================");
            }
        } catch (Exception e) {
            System.err.println("Gagal menginisialisasi Firebase: " + e.getMessage());
            e.printStackTrace();
        }
    }
}