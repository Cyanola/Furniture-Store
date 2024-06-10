package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.Admin;
import com.example.FurnitureStore.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ClientService clientService;
        public Object authenticate(String login, String password) {

            Optional<Client> client = clientService.findByLoginPassword(login, password);
            if (client.isPresent()) {
                return client.get();
            }
           Optional<Admin> admin = adminService.findByLoginPassword(login, password);
            if (admin.isPresent()) {
                return admin.get();
            }


            return null;
        }
    public Object authenticate(UUID login) {

        Optional<Client> client = clientService.findByUUID(login);
        if (client.isPresent()) {
            return client.get();
        }
        Optional<Admin> admin = adminService.findByUUID(login);
        if (admin.isPresent()) {
            return admin.get();
        }


        return null;
    }

    }

