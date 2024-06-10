package com.example.FurnitureStore.Controller;
import com.example.FurnitureStore.Model.Basket;
import com.example.FurnitureStore.Model.Favorites;
import com.example.FurnitureStore.Service.FavoritesService;
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
@RequestMapping("/favorites")
public class FavoritesController {
    @Autowired
    FavoritesService favoritesService;
    @PostMapping("/create/{idcl}")
    public ResponseEntity<Void> createGood( @PathVariable("idcl")UUID idcl ) {
    favoritesService.createFav(idcl);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/clientid/{id}")
    public  ResponseEntity<List<Favorites>> findByClientId(@PathVariable("id") UUID id) {
        try {
           List<Favorites> client=  favoritesService.findByClientId(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/xdclientid/{id}")
    public  ResponseEntity<List<Favorites>> findByClientIdxd(@PathVariable("id") UUID id) {
        try {
            List<Favorites> client=  favoritesService.findByClientIdxd(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteBasket(@PathVariable("id") UUID id) {
        favoritesService.deleteFav(id);
    }

}
