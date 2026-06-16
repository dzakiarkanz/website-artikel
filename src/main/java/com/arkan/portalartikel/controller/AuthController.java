package com.arkan.portalartikel.controller;

import com.arkan.portalartikel.model.User;
import com.arkan.portalartikel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

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

    // 3. Memproses Pendaftaran User Baru dari Form Registrasi
    @PostMapping("/register")
    public String prosesRegister(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("webName", NAMA_WEB);

        // Validasi: Cek apakah username sudah terdaftar di database
        Optional<User> userAda = userRepository.findByUsername(user.getUsername());
        if (userAda.isPresent()) {
            model.addAttribute("errorRegister", "Username sudah digunakan! Silakan pilih nama lain.");
            return "register";
        }

        // ENKRIPSI PASSWORD: Mengubah password plain text menjadi hash BCrypt sebelum disimpan
        String passwordTerenskripsi = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordTerenskripsi);

        // SET ROLE DEFAULT: Semua user yang mendaftar mandiri otomatis diset sebagai PEMBACA
        user.setRole("ROLE_PEMBACA");

        // Simpan ke database lokal
        userRepository.save(user);

        // Redirect ke halaman login dengan parameter sukses
        return "redirect:/login?success";
    }
}