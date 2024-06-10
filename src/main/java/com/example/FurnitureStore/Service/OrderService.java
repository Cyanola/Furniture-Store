package com.example.FurnitureStore.Service;

import com.example.FurnitureStore.Model.*;
import com.example.FurnitureStore.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service

public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public Optional<Orders> findByUUID(UUID uuid) {
        return orderRepository.findById(uuid);
    }
    public List<Orders> findAllByStatus(String status) {
        return orderRepository.findAllByStatus(status);
    }

    public List<Orders> findAllByClient(UUID cl) {
        return orderRepository.findAllByClient(cl);
    }
    public List<Orders> findAllByStatus(String status, UUID uuid) {
        return orderRepository.findAllByStatus(status,uuid);
    }
    public Optional<Orders> updateOrder(UUID id, Orders updatedGood) {
        Optional<Orders>  good = orderRepository.findById(id);
        if (good != null) {
            good.get().setStatus(updatedGood.getStatus());
            return Optional.of(orderRepository.save(good.get()));
        }
        return null;
    }
    @Autowired
    BasketitemsRepository basketitemsRepository;
    @Autowired
    BasketRepository basketRepository;
    @Autowired
    GoodRepository goodRepository;
    @Autowired
    ClientRepository clientRepository;
    public void createOrder(UUID idcl) {
        Client user = clientRepository.findByUUID(idcl).orElseThrow(() -> new RuntimeException("User not found"));
        List<Basket> basket=basketRepository.findByStatus(idcl, 1);

        List<BasketItems> basketItems = basketitemsRepository.findAllByBasketId(user.getUUID(), 1);
        //товары которые лежат в basketitems
List<Good> good =goodRepository.findGoodBasketClientByUUIDList(user.getUUID());
        if (basketItems.isEmpty()) {

        }

        BigDecimal totalAmount = basketItems.stream()
                .map(item -> item.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);


           basketRepository.updateBasket(0, user.getUUID());

        basketItems.forEach(basketItem -> {
            Good goods = basketItem.getGood();

            int newCount = goods.getCount() - basketItem.getQuantity();
            goodRepository.setUpdatedAtById(goods.getUUID(), goods.getName(), goods.getDescription(),
                    goods.getPhoto(), goods.getCost(), goods.getProcent(),
                    newCount, goods.getRating());
        });
       orderRepository.createOrder(Timestamp.valueOf(LocalDateTime.now()),"Передан в доставку",basket.get(0).getUUID(), idcl);
    }
}
