package com.example.FurnitureStore.Controller;
import com.example.FurnitureStore.Model.Admin;
import com.example.FurnitureStore.Service.AdminService;
import lombok.Data;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Data
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("admin")
public class AdminController {


    @Autowired
    public AdminController(AdminService accountService) {
        this.adminService = accountService;
    }
    AdminService adminService;

    @GetMapping("/admin/get/id/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") UUID id) {
        try {
           Optional<Admin> client=  adminService.findByUUID(id);
            return ResponseEntity.ok(client.get());
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }

}
