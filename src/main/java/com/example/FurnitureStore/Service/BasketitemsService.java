package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.*;
import com.example.FurnitureStore.Repository.BasketRepository;
import com.example.FurnitureStore.Repository.BasketitemsRepository;
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


public class BasketitemsService {
    @Autowired
    BasketitemsRepository basketitemsRepository;

    public BasketItems findByUUID(UUID uuid) {
        return basketitemsRepository.findByUUID(uuid);
    }

    public BasketItems findByIdGoodBas(UUID uuidgood, UUID uuidbas) {
        return basketitemsRepository.findByIdGoodBas(uuidgood, uuidbas);
    }
    public BasketItems findByUUID(UUID uuidcl, UUID uuid) {
        return basketitemsRepository.findByUUID(uuidcl, uuid);
    }

    public List<BasketItems> findAllByBasketId(UUID id, int status) {
        return basketitemsRepository.findAllByBasketId(id,status);
    }
    public Integer findCount(UUID id) {
        return  basketitemsRepository.findCount(id);
    }
    public void deleteBasket(UUID id) {
        basketitemsRepository.DeleteBasketitem(id);
    }
    public void createBasket(UUID b,UUID g, int count) {

      Good good = goodRepository.findByUUID(g);

        BigDecimal amount = good.getCost().multiply(BigDecimal.valueOf(count));

      basketitemsRepository.createBasketItem(b,g,count, amount);
    }
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    GoodRepository goodRepository;
    @Autowired
    ClientRepository clientRepository;
    public BasketItems updateBasketitems(UUID id, BasketItems updatedGood) {
        BasketItems  good = basketitemsRepository.findByUUID(id);
        if (good != null) {
            good.setQuantity(updatedGood.getQuantity());
            good.setAmount(updatedGood.getAmount());
            return basketitemsRepository.save(good);
        }
        return null;
    }
}
