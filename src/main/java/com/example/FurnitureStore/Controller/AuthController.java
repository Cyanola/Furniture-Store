package com.example.FurnitureStore.Controller;

import com.example.FurnitureStore.Model.Admin;
import com.example.FurnitureStore.Model.Client;
import com.example.FurnitureStore.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/{login}/{password}")
    public ResponseEntity<?> getUserByCredentials(@PathVariable("login") String login, @PathVariable("password") String password) {
        try {
            Object user = authService.authenticate(login, password);
            if (user instanceof Client) {

                return ResponseEntity.ok((Client) user);
            } else if (user instanceof Admin) {

                return ResponseEntity.ok((Admin) user);

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login or password");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user", e);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") UUID login) {
        try {
            Object user = authService.authenticate(login);
            if (user instanceof Client) {

                return ResponseEntity.ok((Client) user);
            } else if (user instanceof Admin) {

                return ResponseEntity.ok((Admin) user);

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login or password");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user", e);
        }
    }
}
