package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.Admin;
import com.example.FurnitureStore.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service

public class AdminService {
    @Autowired
    private AdminRepository adminRepository;


    public Optional<Admin> findByUUID(UUID uuid) {
        return adminRepository.findByUUID(uuid);
    }

    public Optional<Admin > findByLoginPassword(String login, String password) {
        return adminRepository.findByLoginPassword(login, password);
    }
}
