package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.Delieveres;
import com.example.FurnitureStore.Model.Orders;
import com.example.FurnitureStore.Repository.DelieveresRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@RestController
public class DelieveresService {
    @Autowired
    DelieveresRepository delieveresRepository;
    public Delieveres findByUUID(UUID uuid) {
        return delieveresRepository.findByUUID(uuid);
    }
    //id клиента
    public List<Delieveres> findByClientId(UUID status) {
        return delieveresRepository.findByClientId(status);
    }
    //id клиента и id заказа
    public Delieveres findByOrderId(UUID status, UUID uuid) {
        return delieveresRepository.findByOrderId(status,uuid);
    }

    public Delieveres createDel(Delieveres client) {
        return delieveresRepository.save(client);
    }
}
