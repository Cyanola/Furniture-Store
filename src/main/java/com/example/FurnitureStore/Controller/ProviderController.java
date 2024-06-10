package com.example.FurnitureStore.Controller;
import com.example.FurnitureStore.Model.Good;
import com.example.FurnitureStore.Model.Provider;
import com.example.FurnitureStore.Service.ProviderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Data
@RestController
@RequestMapping("/provider")
@CrossOrigin(origins = "*")
public class ProviderController {
    ProviderService providerService;
    @GetMapping("/all")
    public ResponseEntity<List<Provider>> getAll() {
        List<Provider> allVisitor = providerService.findAll();
        return new ResponseEntity<>(allVisitor, HttpStatus.OK);
    }
@Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Provider> getGoodById(@PathVariable("id") UUID id) {
        try {
            Provider client=  providerService.findByUUID(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createGood(@RequestBody Provider equipment) {
      providerService.createProvider(equipment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteGood(@PathVariable("id") UUID id) {
        providerService.deleteReview(id);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Provider> updateGood(@PathVariable("id") UUID id, @RequestBody Provider updatedVisitor) {
        Provider visitor = providerService.updateProvider(id, updatedVisitor);
        if (visitor != null) {
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
