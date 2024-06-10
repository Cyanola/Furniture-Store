package com.example.FurnitureStore.Controller;

import com.example.FurnitureStore.Model.Client;
import com.example.FurnitureStore.Service.ClientService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("client")
@CrossOrigin(origins = "*")
@Data
public class ClientController {

@Autowired
public ClientController(ClientService accountService) {
    this.clientService = accountService;
}
    ClientService clientService;
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAll() {
        List<Client> allVisitor = clientService.getAll();
        return new ResponseEntity<>(allVisitor, HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public  ResponseEntity<Client> getClientById( @PathVariable("id") UUID id) {
        try {
        Client client=  clientService.findByUUID(id).get();
            return ResponseEntity.ok(client);
        }
     catch (Exception e) {
        throw new RuntimeException("Error fetching visitors", e);
    }
        }

    @PostMapping("/create")
    public ResponseEntity<Void> createEquipment(@RequestBody Client equipment) {
     clientService.createClient(equipment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") UUID id) {
        clientService.deleteClient(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateVisitor(@PathVariable("id") UUID id, @RequestBody Client updatedVisitor) {
        Client visitor = clientService.updateClient(id, updatedVisitor).get();
        if (visitor != null) {
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
