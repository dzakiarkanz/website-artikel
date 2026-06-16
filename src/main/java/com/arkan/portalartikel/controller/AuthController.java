package com.arkan.portalartikel.controller;

import com.arkan.portalartikel.model.User;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String NAMA_WEB = "Tech Pulse";

    // 1. Menampilkan Halaman Login Custom
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        model.addAttribute("webName", NAMA_WEB);
        
        if (error != null) {
            model.addAttribute("errorMessage", "Username atau Password salah!");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Anda telah berhasil keluar.");
        }
        
        return "login"; // Mengarah ke templates/login.html
    }

    // 2. Menampilkan Halaman Registrasi Akun Pembaca
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("webName", NAMA_WEB);
        model.addAttribute("user", new User());
        return "register"; // Mengarah ke templates/register.html
    }

    // 3. Memproses Pendaftaran User Baru dari Form Registrasi (Dialihkan ke Firebase NoSQL)
    @PostMapping("/register")
    public String prosesRegister(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("webName", NAMA_WEB);

        try {
            Firestore db = FirestoreClient.getFirestore();

            // Validasi NoSQL: Cek apakah username sudah terdaftar di koleksi "users" Firebase
            QuerySnapshot userCheck = db.collection("users")
                    .whereEqualTo("username", user.getUsername())
                    .get().get();

            if (!userCheck.isEmpty()) {
                model.addAttribute("errorRegister", "Username sudah digunakan! Silakan pilih nama lain.");
                return "register";
            }

            // ENKRIPSI PASSWORD: Mengubah password plain text menjadi hash BCrypt sebelum dikirim ke cloud
            String passwordTerenskripsi = passwordEncoder.encode(user.getPassword());

            // Siapkan payload data dalam bentuk Map NoSQL untuk Firebase
            Map<String, Object> userData = new HashMap<>();     
            userData.put("username", user.getUsername());
            userData.put("password", passwordTerenskripsi);
            userData.put("role", "ROLE_PEMBACA");

            // Simpan dokumen baru ke dalam koleksi "users" di Firebase Firestore
            db.collection("users").add(userData).get();
            System.out.println("User baru berhasil didaftarkan ke Firebase Firestore!");

        } catch (Exception e) {
            System.err.println("Gagal memproses registrasi ke Firebase: " + e.getMessage());
            model.addAttribute("errorRegister", "Terjadi kesalahan sistem saat mendaftar.");
            return "register";
        }

        // Redirect ke halaman login dengan parameter sukses
        return "redirect:/login?success";
    }
}