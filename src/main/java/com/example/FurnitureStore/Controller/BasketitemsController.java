package com.example.FurnitureStore.Controller;
import com.example.FurnitureStore.Model.Basket;
import com.example.FurnitureStore.Model.BasketItems;
import com.example.FurnitureStore.Model.Good;
import com.example.FurnitureStore.Service.BasketService;
import com.example.FurnitureStore.Service.BasketitemsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/basketitems")
public class BasketitemsController {
    BasketitemsService basketitemsService;
@Autowired
    public BasketitemsController(BasketitemsService basketitemsService) {
        this.basketitemsService = basketitemsService;
    }

    @GetMapping("/id/{id}")
    public  ResponseEntity<BasketItems> getGoodById( @PathVariable("id") UUID id) {
        try {
            BasketItems client=  basketitemsService.findByUUID(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/idgb/{idg}/{idb}")
    public  ResponseEntity<BasketItems> findByIdGoodBas( @PathVariable("idg") UUID idcl,@PathVariable("idb") UUID id) {
        try {
            BasketItems client=  basketitemsService.findByIdGoodBas(idcl,id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }


    @GetMapping("/id/{idcl}/{id}")
    public  ResponseEntity<BasketItems> getGoodById( @PathVariable("idcl") UUID idcl,@PathVariable("id") UUID id) {
        try {
            BasketItems client=  basketitemsService.findByUUID(idcl,id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }

    @PostMapping("/create/{idbas}/{idgood}/{quantity}")
    public ResponseEntity<Void> createBasketItems(@PathVariable("idbas") UUID idbas,@PathVariable("idgood") UUID idgood,
                                                         @PathVariable("quantity") int quantity
                                                        ) {
        basketitemsService.createBasket(idbas,idgood,quantity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteGood(@PathVariable("id") UUID id) {
        basketitemsService.deleteBasket(id);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<BasketItems> updateGood(@PathVariable("id") UUID id, @RequestBody BasketItems updatedVisitor) {
        BasketItems visitor = basketitemsService.updateBasketitems(id, updatedVisitor);
        if (visitor != null) {
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/basketid/{id}/{st}")
    public ResponseEntity<List<BasketItems>> findAllByBasketId(@PathVariable("id") UUID id,@PathVariable("status") int st) {
        List<BasketItems> allVisitor = basketitemsService.findAllByBasketId(id,st);
        return new ResponseEntity<>(allVisitor, HttpStatus.OK);
    }
    @GetMapping("/count/{id}")
    public  Integer findCount(@PathVariable("id") UUID id) {
        try {
          Integer count =  basketitemsService.findCount(id);
          return count;
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
}
