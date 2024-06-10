package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.*;
import com.example.FurnitureStore.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service

public class FavitemsService {
    @Autowired
    private FavitemsRepository favitemsRepository;

    public Favitems findByUUID(UUID uuid) {
        return favitemsRepository.findByUUID(uuid);
    }

    public Favitems findByUUID(UUID uuidcl, UUID uuid) {
        return favitemsRepository.findByUUID(uuidcl, uuid);
    }

    public Favitems findByIdGoodFav(UUID uuidg, UUID uuid) {
        return favitemsRepository.findByIdGoodFav(uuidg, uuid);
    }
    public List<Favitems> findAllByBasketId(UUID id) {
        return favitemsRepository.findAllByBasketId(id);
    }
    public Integer findCount(UUID id) {
        return  favitemsRepository.findCount(id);
    }
    public void deleteBasket(UUID id) {
        favitemsRepository.DeleteBasketitem(id);
    }

    @Autowired
    FavoritesRepository favoritesRepository;
    @Autowired
    GoodRepository goodRepository;
    @Autowired
    ClientRepository clientRepository;
    public void createFavitem(UUID fav, UUID g) {
  favitemsRepository.createOrder(fav,g);

        Favorites favorites = favoritesRepository.findByUUID(fav);
        if (favorites == null) {
            throw new RuntimeException("Favorites record with ID " + fav + " not found");
        }

    }
}
