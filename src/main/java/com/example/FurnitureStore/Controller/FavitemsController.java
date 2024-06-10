package com.example.FurnitureStore.Controller;
import com.example.FurnitureStore.Model.BasketItems;
import com.example.FurnitureStore.Model.Favitems;
import com.example.FurnitureStore.Model.Favorites;
import com.example.FurnitureStore.Service.FavitemsService;
import com.example.FurnitureStore.Service.FavoritesService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Data
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/favitems")
public class FavitemsController {
    FavitemsService favoritesService;
    @GetMapping("/idgf/{idg}/{idf}")
    public  ResponseEntity<Favitems> findByIdGoodFav( @PathVariable("idg") UUID idcl,@PathVariable("idf") UUID id) {
        try {
            Favitems client=  favoritesService.findByIdGoodFav(idcl,id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }

    @Autowired
   public  FavitemsController(FavitemsService favoritesService){
        this.favoritesService=favoritesService;
    }
    @GetMapping("/favid/{id}")
    public ResponseEntity<List<Favitems>> findAllByBasketId(@PathVariable("id") UUID id) {
        List<Favitems> allVisitor = favoritesService.findAllByBasketId(id);
        return new ResponseEntity<>(allVisitor, HttpStatus.OK);
    }
    @GetMapping("/count/{id}")
    public  Integer findCount(@PathVariable("id") UUID id) {
        try {
            Integer count =  favoritesService.findCount(id);
            return count;
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/id/{id}")
    public  ResponseEntity<Favitems> getGoodById( @PathVariable("id") UUID id) {
        try {
            Favitems client=  favoritesService.findByUUID(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @PostMapping("/create/{idfav}/{idgood}")
    public ResponseEntity<Void> createBasketItems(@PathVariable("idfav") UUID idbas,@PathVariable("idgood") UUID idgood) {
      favoritesService.createFavitem(idbas,idgood);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteGood(@PathVariable("id") UUID id) {
        favoritesService.deleteBasket(id);
    }

}
