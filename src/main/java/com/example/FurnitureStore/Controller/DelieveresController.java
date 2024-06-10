package com.example.FurnitureStore.Controller;

import com.example.FurnitureStore.Model.Delieveres;
import com.example.FurnitureStore.Model.Good;
import com.example.FurnitureStore.Service.DelieveresService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.instrument.ClassDefinition;
import java.util.List;
import java.util.UUID;

@Data
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/delieveres")
public class DelieveresController {
    DelieveresService delieveresService;
    @Autowired
    public DelieveresController(DelieveresService delieveresService) {
        this.delieveresService = delieveresService;
    }
    @PostMapping("/create")
    public ResponseEntity<Delieveres> createGood(@RequestBody Delieveres equipment) {
        Delieveres createdEquipment = delieveresService.createDel(equipment);
        return new ResponseEntity<>(createdEquipment, HttpStatus.CREATED);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Delieveres> getGoodById(@PathVariable("id") UUID id) {
        try {
            Delieveres client=  delieveresService.findByUUID(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/del/{idcl}/{id}")
    public ResponseEntity<Delieveres> findByOrderId(@PathVariable("idcl") UUID idcl,@PathVariable("idcl") UUID id) {
        try {
            Delieveres client=  delieveresService.findByOrderId(idcl,id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }

    @GetMapping("/list/category/{id}")
    public  ResponseEntity<List<Delieveres>> findByClientId(@PathVariable("id") UUID id) {
        try {
            List<Delieveres>client=  delieveresService.findByClientId(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }


}
