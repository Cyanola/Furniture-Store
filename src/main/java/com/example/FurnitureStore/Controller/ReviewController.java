package com.example.FurnitureStore.Controller;
import com.example.FurnitureStore.Model.Good;
import com.example.FurnitureStore.Model.Reviews;
import com.example.FurnitureStore.Service.ReviewService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Data
@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*")
public class ReviewController {

    ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/id/{id}")
    public  ResponseEntity<Reviews> getGoodById( @PathVariable("id") UUID id) {
        try {
            Reviews client=  reviewService.findByUUID(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/idgood/{id}")
    public  ResponseEntity<List<Reviews>> findByUUIDGood( @PathVariable("id") UUID id) {
        try {
            List<Reviews>client=  reviewService.findByUUIDGood(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/list/idcl/{id}")
    public  ResponseEntity<List<Reviews>> findReviewByUUIDcl( @PathVariable("id") UUID id) {
        try {
            List<Reviews>client=  reviewService.findReviewByUUIDcl(id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/id/{idg}/{id}")
    public  ResponseEntity<Reviews> findByUUID( @PathVariable("idg") UUID idr ,@PathVariable("id") UUID id) {
        try {
            Reviews client=  reviewService.findByUUID(idr,id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @GetMapping("/id/{idr}/{id}")
    public  ResponseEntity<Reviews> findByUUIDcl( @PathVariable("idr") UUID idr ,@PathVariable("id") UUID id) {
        try {
            Reviews client=  reviewService.findByUUIDcl(idr,id);
            return ResponseEntity.ok(client);
        }
        catch (Exception e) {
            throw new RuntimeException("Error fetching visitors", e);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Reviews> createGood(@RequestBody Reviews equipment) {
        Reviews createdEquipment = reviewService.createReview(equipment);
        return new ResponseEntity<>(createdEquipment, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteGood(@PathVariable("id") UUID id) {
        reviewService.deleteReview(id);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Reviews> updateGood(@PathVariable("id") UUID id, @RequestBody Reviews updatedVisitor) {
        Reviews visitor = reviewService.updateReview(id, updatedVisitor);
        if (visitor != null) {
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
