package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.Client;
import com.example.FurnitureStore.Model.Favorites;
import com.example.FurnitureStore.Repository.ClientRepository;
import com.example.FurnitureStore.Repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service

public class FavoritesService {
    @Autowired
    private FavoritesRepository favoritesRepository;
    public Favorites findByUUID(UUID uuid) {
        return favoritesRepository.findByUUID(uuid);
    }

    public List<Favorites> findByClientId(UUID id) {
        return favoritesRepository.findByClientId(id);
    }
    public List<Favorites> findByClientIdxd(UUID id) {
        return favoritesRepository.findByClientIdxd(id);
    }

    public void deleteFav(UUID id) {
        favoritesRepository.deleteById(id);
    }
    @Autowired
    ClientRepository clientRepository;
    public void createFav(UUID cl) {
        Client user = clientRepository.findByUUID(cl).orElseThrow(() -> new RuntimeException("User not found"));

      favoritesRepository.createFavorite(cl);

    }
}
