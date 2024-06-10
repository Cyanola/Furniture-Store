package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.Good;
import com.example.FurnitureStore.Repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service

public class GoodService {
    @Autowired
    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }
    GoodRepository goodRepository;
    public Good findByUUID(UUID uuid) {
        return goodRepository.findByUUID(uuid);
    }

    public List<Good> findGoodReviewByUUID() {
        return goodRepository.findGoodReviewByUUID();
    }

    public List<Good> findAll(){
        return goodRepository.findAll();
    }
    public List<Good> findGoodReviewClientByUUID(UUID idcl) {
        return goodRepository.findGoodReviewClientByUUID(idcl);
    }
    public List<Good> findGoodBasketClientByUUIDList( UUID uuidcl) {
        return  goodRepository.findGoodBasketClientByUUIDList(uuidcl);
    }
    public Good findGoodBasketClientByUUIDOne(UUID uuid, UUID uuidcl) {
        return  goodRepository.findGoodBasketClientByUUIDOne(uuid,uuidcl);
    }
    public  List<Good> findAllByCategory(String category) {return  goodRepository.findAllByCategory(category);}
    public List<Good> findAllByName(String name) {
        return  goodRepository.findAllByName(name);
    }
    public List<Good> findAllByOrderByCostDesc() {
        return  goodRepository.findAllByOrderByCostDesc();
    }
    public List<Good> findAllByOrderByCostAsc() {
        return  goodRepository.findAllByOrderByCostAsc();
    }
    public List<Good> findAllByProcentAsc(int p1,int p2) {
        return  goodRepository.findAllByProcentAsc(p1,p2);
    }
    public List<Good> findAllByProcentDesc(int p1,int p2) {
        return  goodRepository.findAllByProcentDesc(p1,p2);
    }
    public List<Good> findAllByCostBetween(BigDecimal p1, BigDecimal p2) {
        return  goodRepository.findAllByCostBetween(p1, p2);
    }
    public List<Good> findByProviderCompany(String name) {
        return  goodRepository.findByProviderCompany(name);
    }
    public List<Good> findAllByRating(double p) {
        return  goodRepository.findAllByRating(p);
    }
    public List<Good> findAllByRating() {
        return  goodRepository.findAllByRating();
    }
    public List<Good> findInstock() {
        return  goodRepository.findInstock();
    }
    public void deleteGood(UUID id) {
        goodRepository.deleteById(id);
    }
    public void createGood(Good client) {
         goodRepository.save(client);
    }
    public void updateGood(UUID id, Good updatedGood) {
        Good  good = goodRepository.findByUUID(id);
        if (good != null) {
            good.setName(updatedGood.getName());
            good.setDescription(updatedGood.getDescription());
            good.setPhoto(updatedGood.getPhoto());
            good.setCost(updatedGood.getCost());
            good.setPhoto(updatedGood.getPhoto());
            good.setProcent(updatedGood.getProcent());
            good.setCount(updatedGood.getCount());
            good.setRating(updatedGood.getRating());
            goodRepository.save(good);
        }

    }
}
