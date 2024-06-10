package com.example.FurnitureStore.Controller;
import com.example.FurnitureStore.Model.Good;
import com.example.FurnitureStore.Model.Orders;
import com.example.FurnitureStore.Service.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderController {
OrderService orderService;
@Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/list/status/{status}")
    public  ResponseEntity<List<Orders>> findAllByStatus(@PathVariable("status") String id) {
        try {
            List<Orders>client=  orderService.findAllByStatus(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/client/{status}")
    public  ResponseEntity<List<Orders>> findAllByClient(@PathVariable("status") UUID id) {
        try {
            List<Orders>client=  orderService.findAllByClient(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/statusid/{status}/{id}")
    public  ResponseEntity<List<Orders>> findAllByStatus(@PathVariable("status") String id, @PathVariable("id") UUID id_) {
        try {
            List<Orders>client=  orderService.findAllByStatus(id, id_);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @PostMapping("/create/{idcl}")
    public ResponseEntity<Void> createGood( @PathVariable("idcl")UUID idcl ) {
      orderService.createOrder(idcl);
       // return new ResponseEntity<>(createdEquipment, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/id/{id}")
    public  ResponseEntity<Orders> getGoodById( @PathVariable("id") UUID id) {
        try {
         Optional  < Orders > client=  orderService.findByUUID(id);
            return ResponseEntity.ok(client.get());
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<Orders> updateGood(@PathVariable("id") UUID id, @RequestBody Orders updatedVisitor) {
      Optional<Orders> visitor = orderService.updateOrder(id, updatedVisitor);
        if (visitor != null) {
            return new ResponseEntity<>(visitor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
