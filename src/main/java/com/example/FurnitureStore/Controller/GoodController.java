package com.example.FurnitureStore.Controller;
import com.example.FurnitureStore.Model.Good;
import com.example.FurnitureStore.Service.GoodService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/good")
public class GoodController {
    @Autowired
    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    GoodService goodService;
    @GetMapping("/all")
    public ResponseEntity<List<Good>> getAll() {
        List<Good> allVisitor = goodService.findAll();
        return new ResponseEntity<>(allVisitor, HttpStatus.OK);
    }
    @GetMapping("/gbcone/{id}/{idcl}")
    public  ResponseEntity<Good> findGoodBasketClientByUUIDOne( @PathVariable("id") UUID id, @PathVariable("idcl") UUID idcl) {
        try {
            Good client=  goodService.findGoodBasketClientByUUIDOne(id,idcl);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/id/{id}")
    public  ResponseEntity<Good> getGoodById( @PathVariable("id") UUID id) {
        try {
            Good client=  goodService.findByUUID(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }

    @GetMapping("/list/category/{category}")
    public  ResponseEntity<List<Good>> findAllByCategory( @PathVariable("category") String id) {
        try {
            List<Good>client=  goodService.findAllByCategory(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/reviews")
    public  ResponseEntity<List<Good>> findGoodReviewByUUID() {
        try {
            List<Good>client=  goodService.findGoodReviewByUUID();
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/instock")
    public  ResponseEntity<List<Good>> findInstock() {
        try {
            List<Good>client=  goodService.findInstock();
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/rating")
    public  ResponseEntity<List<Good>> findAllByRating() {
        try {
            List<Good>client=  goodService.findAllByRating();
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/rating/{id}")
    public  ResponseEntity<List<Good>> findAllByRating( @PathVariable("id") double id) {
        try {
            List<Good>client=  goodService.findAllByRating(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/company/{company}")
    public  ResponseEntity<List<Good>> findByProviderCompany( @PathVariable("company") String id) {
        try {
            List<Good>client=  goodService.findByProviderCompany(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/name/{id}")
    public  ResponseEntity<List<Good>> findAllByName( @PathVariable("id") String id) {
        try {
            List<Good>client=  goodService.findAllByName(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/costdesc")
    public  ResponseEntity<List<Good>> findAllByOrderByCostDesc() {
        try {
            List<Good>client=  goodService.findAllByOrderByCostDesc();
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/costasc")
    public  ResponseEntity<List<Good>> findAllByOrderByCostAsc() {
        try {
            List<Good>client=  goodService.findAllByOrderByCostAsc();
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/procentasc/{p1}/{p2}")
    public  ResponseEntity<List<Good>> findAllByProcentAsc(int p1, int p2) {
        try {
            List<Good>client=  goodService.findAllByProcentAsc(p1, p2);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/procentdesc/{p1}/{p2}")
    public  ResponseEntity<List<Good>> findAllByProcentDesc(int p1, int p2) {
        try {
            List<Good>client=  goodService.findAllByProcentDesc(p1, p2);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/costbetween/{p1}/{p2}")
    public  ResponseEntity<List<Good>> findAllByCostBetween(BigDecimal p1, BigDecimal p2) {
        try {
            List<Good>client=  goodService.findAllByCostBetween(p1, p2);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }

    @GetMapping("/list/idcl/{id}")
    public  ResponseEntity<List<Good>> findGoodBasketClientByUUIDList( @PathVariable("id") UUID id) {
        try {
            List<Good>client=  goodService.findGoodBasketClientByUUIDList(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/goodreviews/{id}")
    public  ResponseEntity<List<Good>> findGoodReviewClientByUUID(@PathVariable("id") UUID id) {
        try {
           List<Good>client=  goodService.findGoodReviewClientByUUID(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Void> createGood(@RequestBody Good equipment) {
     goodService.createGood(equipment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteGood(@PathVariable("id") UUID id) {
        goodService.deleteGood(id);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Void> updateGood(@PathVariable("id") UUID id, @RequestBody Good updatedVisitor) {
      goodService.updateGood(id, updatedVisitor);

            return new ResponseEntity<>(HttpStatus.OK);
    }


}
