package com.arkan.portalartikel.service;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Firestore db = FirestoreClient.getFirestore();

            // 1. Cari data user di Firebase berdasarkan username yang dimasukkan di form login
            QuerySnapshot userQuery = db.collection("users")
                    .whereEqualTo("username", username)
                    .get().get();

            // 2. Jika user tidak ditemukan di Firebase, lempar error
            if (userQuery.isEmpty()) {
                throw new UsernameNotFoundException("User tidak ditemukan di Firebase: " + username);
            }

            // 3. Ambil data dokumen pertama yang cocok
            Map<String, Object> userData = userQuery.getDocuments().get(0).getData();
            String passwordTerenskripsi = userData.get("password").toString();
            String role = userData.get("role").toString(); // Contoh: ROLE_ADMIN atau ROLE_PEMBACA

            // 4. Kembalikan objek User resmi Spring Security dengan password hash dan rolenya
            return new User(
                    username,
                    passwordTerenskripsi,
                    Collections.singletonList(new SimpleGrantedAuthority(role))
            );

        } catch (Exception e) {
            throw new UsernameNotFoundException("Gagal melakukan otentikasi via Firebase", e);
        }
    }
}