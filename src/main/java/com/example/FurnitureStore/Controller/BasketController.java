package com.example.FurnitureStore.Controller;
import com.example.FurnitureStore.Model.Basket;
import com.example.FurnitureStore.Model.BasketItems;
import com.example.FurnitureStore.Model.Good;
import com.example.FurnitureStore.Repository.BasketitemsRepository;
import com.example.FurnitureStore.Service.BasketService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/basket")
public class BasketController {
    BasketitemsRepository basketitemsRepository;
    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }
    BasketService basketService;

    @GetMapping("/all")
    public ResponseEntity<List<Basket>> getAll() {
        List<Basket> allVisitor = basketService.findAll();
        return new ResponseEntity<>(allVisitor, HttpStatus.OK);
    }
    @GetMapping("/list/{id}/{idcl}")
    public  ResponseEntity<Basket> findByUUIDGood( @PathVariable("id") UUID id,@PathVariable("idcl") UUID idcl) {
        try {
            Optional<Basket>client=  basketService.findByUUIDGood(id,idcl);
            return ResponseEntity.ok(client.get());
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/clientid/{id}")
    public  ResponseEntity<List<Basket>> findByClientId( @PathVariable("id") UUID id) {
        try {
            List<Basket> client=  basketService.findByClientId(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @PostMapping("/create/{idcl}")
    public ResponseEntity<Void> createGood( @PathVariable("idcl")UUID idcl ) {
      basketService.createBasket(idcl);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteBasket(@PathVariable("id") UUID id) {
        basketService.deleteBasket(id);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Basket> updateBasket(@PathVariable("id") UUID id, @RequestBody Basket updatedVisitor) {
       Optional< Basket > visitor = basketService.updateBasket(id, updatedVisitor);
        if (visitor != null) {
            return new ResponseEntity<>(visitor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("xdstatus/{id}/{status}")
    public  ResponseEntity<List<Basket>> findByUUIDxd( @PathVariable("id") UUID id,@PathVariable("status") int status) {
        try {
            List< Basket> client=  basketService.findByUUIDxd(id,status);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }


    @GetMapping("status/{id}/{status}")
    public  ResponseEntity<List<Basket>> findByUUID( @PathVariable("id") UUID id,@PathVariable("status") int status) {
        try {
         List< Basket> client=  basketService.findByUUID(id,status);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/id/{id}")
    public  ResponseEntity<Basket> getGoodById( @PathVariable("id") UUID id) {
        try {
            Optional<Basket> client=  basketService.findByUUID(id);
            return ResponseEntity.ok(client.get());
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @PostMapping("/{userId}/buyAll")
    public ResponseEntity<Void> buyAll(@PathVariable("userId") UUID userId) {
        try {
            List<Basket> basket = basketService.findByUUID(userId,1);
            if (basket == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            for (Basket b  :basket        ) {
                List<BasketItems> items = b.getBasketItems();
                for (BasketItems item : items) {
                    basketitemsRepository.delete(item);
                }
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
