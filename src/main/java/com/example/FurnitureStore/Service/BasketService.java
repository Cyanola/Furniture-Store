package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.Basket;
import com.example.FurnitureStore.Model.BasketItems;
import com.example.FurnitureStore.Model.Client;
import com.example.FurnitureStore.Model.Good;
import com.example.FurnitureStore.Repository.BasketRepository;
import com.example.FurnitureStore.Repository.ClientRepository;
import com.example.FurnitureStore.Repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service

public class BasketService {

    public List<Basket> findAll(){
        return basketRepository.findAll();
    }
    @Autowired
    private BasketRepository basketRepository;
    public Optional<Basket> findByUUID(UUID uuid) {
        return basketRepository.findByUUID(uuid);
    }
    public List<Basket> findByUUID(UUID uuidcl, int status) {
        return basketRepository.findByStatus(uuidcl,status);
    }
    public List<Basket> findByUUIDxd(UUID uuidcl, int status) {
        return basketRepository.findByStatusxd(uuidcl,status);
    }
    public Optional<Basket> updateBasket(UUID id, Basket updatedClient) {
        Optional<Basket > client = basketRepository.findByUUID(id);
        if (client != null) {
            client.get().setStatus(updatedClient.getStatus());

            return Optional.of(basketRepository.save(client.get()));
        }
        return null;
    }

    public List<Basket> findByClientId(UUID id) {
        return basketRepository.findByClientId(id);
    }
    public Optional<Basket> findByUUIDGood(UUID id, UUID cl) {
      return  basketRepository.findByUUIDGood(id,cl);
    }
    public void deleteBasket(UUID id) {
        basketRepository.deleteById(id);
    }

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    GoodRepository goodRepository;
    public void createBasket(UUID cl) {
        Client user = clientRepository.findByUUID(cl).orElseThrow(() -> new RuntimeException("User not found"));
     basketRepository.createBasket(cl);
    }

}
