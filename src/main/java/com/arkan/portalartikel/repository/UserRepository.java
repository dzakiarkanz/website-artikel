package com.arkan.portalartikel.repository;

import com.arkan.portalartikel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Fungsi khusus untuk mencari data user berdasarkan username saat login
    Optional<User> findByUsername(String username);
}