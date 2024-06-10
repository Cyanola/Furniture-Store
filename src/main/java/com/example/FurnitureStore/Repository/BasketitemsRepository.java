package com.example.FurnitureStore.Repository;
import com.example.FurnitureStore.Model.BasketItems;
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
public interface BasketitemsRepository extends JpaRepository<BasketItems, UUID>{
//для проверки наличия товара в корзине
    @Query(value="SELECT g.* FROM Basketitems g join basket b on b.id_bas=g.id_bas join " +
            "good c on c.id_good=g.id_good where g.id_good = :uuid and b.id_bas=:uuid2",nativeQuery = true)
    BasketItems findByIdGoodBas(@Param("uuid") UUID uuid,@Param("uuid2") UUID uuid2);

    //1 элемент из элементов корзины

    @Query(value="SELECT g.* FROM Basketitems g join basket b on b.id_bas=g.id_bas join " +
            "client c on c.id_cl=b.id_cl where g.id_basket_item = :uuid",nativeQuery = true)
    BasketItems findByUUID(@Param("uuid") UUID uuid);
    //1 элемент из элементов корзины

    @Query(value="SELECT g.* FROM Basketitems g join basket b on b.id_bas=g.id_bas join client c on c.id_cl=b.id_cl where c.id_cl=:uuidcl and g.id_basket_item = :uuid",
    nativeQuery = true)
    BasketItems findByUUID(@Param("uuidcl") UUID uuidcl,@Param("uuid") UUID uuid);
    //все элементы  корзины клиента
    @Query(value="SELECT g.*  FROM Basketitems g join basket b on b.id_bas=g.id_bas join client c on c.id_cl=b.id_cl where c.id_cl=:uuid and b.status =:status",nativeQuery = true)
    List<BasketItems> findAllByBasketId(@Param("uuid") UUID basketId,@Param("status") int status );

    //нахождение количества товаров в корзине пользователя
    @Query(value="SELECT COUNT(g.*) FROM basketitems g join basket b on b.id_bas=g.id_bas join client c on c.id_cl=b.id_cl where c.id_cl=:uuid and status=1",nativeQuery = true)
  Integer findCount(@Param("uuid") UUID uuid);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM basketitems where id_basket_item=:id",nativeQuery = true)
    void DeleteBasketitem(@Param("id") UUID idbas);

    @Transactional
    @Modifying
    @Query(value="INSERT INTO basketitems(id_basket_item,id_bas, id_good, quantity,amount) " +
            "values(uuid_generate_v4(), :idbas, :idgood,:quantity,:amount)",nativeQuery = true)
    void createBasketItem(@Param("idbas") UUID idbas, @Param("idgood") UUID idgood, @Param("quantity") int count, @Param("amount") BigDecimal amount);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Basketitems c SET c.quantity=:quantity, c.amount=:amount where id_basket_item=:id", nativeQuery = true)
    void setUpdatedAtById(@Param("id") UUID id,@Param("quantity") int quantity,@Param("amount") BigDecimal amount);
}
