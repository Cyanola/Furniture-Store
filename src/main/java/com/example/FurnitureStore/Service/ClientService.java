package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.Client;
import com.example.FurnitureStore.Repository.BasketRepository;
import com.example.FurnitureStore.Repository.ClientRepository;
import com.example.FurnitureStore.Repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service

public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findByUUID(UUID uuid) {
        return clientRepository.findByUUID(uuid);
    }
    public Optional<Client> findByLoginPassword(String login, String password) {
        return clientRepository.findByLoginPassword(login, password);
    }
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    FavoritesRepository favoritesRepository;
    public void createClient(Client client) {
        var cl=clientRepository.save(client);
basketRepository.createBasket(cl.getUUID());
favoritesRepository.createFavorite(cl.getUUID());
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
    public Optional<Client> updateClient(UUID id, Client updatedClient) {
       Optional<Client > client = clientRepository.findByUUID(id);
        if (client != null) {
            client.get().setSurname(updatedClient.getSurname());
            client.get().setName(updatedClient.getName());
            client.get().setPatr(updatedClient.getPatr());
            client.get().setPhone(updatedClient.getPhone());
            client.get().setEmail(updatedClient.getEmail());
            client.get().setPhoto(updatedClient.getPhoto());
            client.get().setPassword(updatedClient.getPassword());
            client.get().setAddress(updatedClient.getAddress());
            client.get().setLogin(updatedClient.getLogin());
            return Optional.of(clientRepository.save(client.get()));
        }
        return null;
    }


}
