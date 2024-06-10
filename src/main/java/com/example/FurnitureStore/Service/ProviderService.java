package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.Good;
import com.example.FurnitureStore.Model.Provider;
import com.example.FurnitureStore.Repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service

public class ProviderService {
    @Autowired
    ProviderRepository providerRepository;
    public Provider findByUUID(UUID uuid) {
        return providerRepository.findByUUID(uuid);
    }


    public List<Provider> findAll(){
        return providerRepository.findAll();
    }

    public void deleteReview(UUID id) {
        providerRepository.deleteById(id);
    }
    public Provider updateProvider(UUID id, Provider updatedGood) {
        Provider  good = providerRepository.findByUUID(id);
        if (good != null) {
            good.setCompany(updatedGood.getCompany());
            good.setEmail(updatedGood.getEmail());
            good.setPhone(updatedGood.getPhone());
            return providerRepository.save(good);
        }
        return null;
    }
    public void createProvider(Provider client) {
        providerRepository.save(client);
    }

}
