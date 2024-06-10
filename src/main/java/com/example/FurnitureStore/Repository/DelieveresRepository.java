package com.example.FurnitureStore.Repository;
import com.example.FurnitureStore.Model.Delieveres;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface DelieveresRepository extends JpaRepository<Delieveres, UUID> {
    @Query(value = "SELECT d  FROM Delieveres d WHERE d.id_del = :uuid",nativeQuery = true)
    Delieveres findByUUID(@Param("uuid") UUID uuid);
//список доставок для клиента
    @Query(value = "SELECT d  FROM Delieveres d JOIN Orders o on o.id_order=d.id_order join basket b on b.id_bas=o.id_bas " +
            "  join basketitems bb on bb.id_bas=b.id_bas join client c on c.id_cl=o.id_cl where c.id_cl=:id",nativeQuery = true)
    List<Delieveres > findByClientId(@Param("id") UUID Id);
    //найти доставку по id клиента и заказа
    @Query(value = "SELECT d  FROM Delieveres d JOIN Orders o on o.id_order=d.id_order join basket b on b.id_bas=o.id_bas " +
            "  join basketitems bb on bb.id_bas=b.id_bas join client c on c.id_cl=o.id_cl where c.id_cl=:id and d.id_order = :uuid",nativeQuery = true)
    Delieveres findByOrderId(@Param("id") UUID Id,@Param("uuid") UUID orderId);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Delieveres(id_del, date_, amount, id_order) " +
            "values (uuid_generate_v4(), :date_,:amount, :id)",nativeQuery = true)
    void createBasket(@Param("date_") Timestamp date_,@Param("amount")  BigDecimal amount,@Param("id")   UUID id);

}
