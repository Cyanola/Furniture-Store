package com.example.FurnitureStore.Repository;
import com.example.FurnitureStore.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID>{
    Optional<Orders> findById(@Param("uuid") UUID uuid);
    @Transactional
    @Modifying
    @Query(value="INSERT INTO orders(id_order, date_oform, status, id_bas,id_cl) " +
            "values(uuid_generate_v4(), :date_oform, :status,:id_bas,:id_cl)",nativeQuery = true)
    void createOrder(@Param("date_oform") Timestamp date_, @Param("status") String status, @Param("id_bas") UUID id, @Param("id_cl") UUID idcl);

     //поиск по статусу заказа для клиента
     @Query(value = "SELECT g.* FROM Orders g join basket b on b.id_bas=g.id_bas " +
             "join basketitems bb on bb.id_bas=b.id_bas join client c on c.id_cl=b.id_cl where g.status=:status and  c.id_cl=:id",nativeQuery = true)
     List<Orders> findAllByStatus(@Param("status") String status, @Param("id") UUID id_cl);

    //поиск заказов для клиента
    @Query(value = "SELECT g.* FROM Orders g join basket b on b.id_bas=g.id_bas " +
            "join basketitems bb on bb.id_bas=b.id_bas join good on good.id_good=bb.id_good join client c on c.id_cl=b.id_cl where c.id_cl=:id",nativeQuery = true)
    List<Orders> findAllByClient(@Param("id") UUID id_cl);

    //поиск по статусу заказа
    @Query(value = "SELECT g.* FROM Orders g join basket b on b.id_bas=g.id_bas " +
            "join basketitems bb on bb.id_bas=b.id_bas join client c on c.id_cl=b.id_cl where g.status=:status ",nativeQuery = true)
    List<Orders> findAllByStatus(@Param("status") String status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Orders SET status=:status",nativeQuery = true)
    void updateOrder(String status);
}
