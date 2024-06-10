package com.example.FurnitureStore.Repository;
import com.example.FurnitureStore.Model.BasketItems;
import com.example.FurnitureStore.Model.Favitems;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavitemsRepository extends JpaRepository<Favitems, UUID> {


    //для проверки наличия товара в корзине
    @Query(value="SELECT g.* FROM Favitems g join favorites b on b.id_fav=g.id_fav join " +
            "good c on c.id_good=g.id_good where g.id_good = :uuid and b.id_fav=:uuid2",nativeQuery = true)
    Favitems findByIdGoodFav(@Param("uuid") UUID uuid, @Param("uuid2") UUID uuid2);

    //1 элемент из элементов корзины
    @Query(value = "SELECT f.* FROM Favitems f JOIN favorites b on b.id_fav=f.id_fav JOIN client c on c.id_cl=b.id_cl " +
            "JOIN Basket ba on c.id_cl =ba.id_cl " +
            " WHERE f.id_fav_item = :uuid AND ba.status=1",nativeQuery = true)
    Favitems findByUUID(@Param("uuid") UUID uuid);

    @Transactional
    @Modifying
    @Query(value="INSERT INTO favitems(id_fav_item, id_fav, id_good) " +
            "values(uuid_generate_v4(), :idfav, :idgood)",nativeQuery = true)
    void createOrder(@Param("idfav") UUID idfavl,@Param("idgood") UUID idgood);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM favitems where id_fav_item=:id",nativeQuery = true)
    void DeleteBasketitem(@Param("id") UUID idbas);

    //1 элемент из элементов корзины

    @Query(value = "SELECT g.* FROM favitems g join favorites b on b.id_fav=g.id_fav join client c on c.id_cl=b.id_cl where " +
            "c.id_cl=:uuidcl and g.id_fav_item = :uuid",nativeQuery = true)
    Favitems findByUUID(@Param("uuidcl") UUID uuidcl,@Param("uuid") UUID uuid);
    //все элементы  корзины клиента
    @Query(value = "SELECT g.*  FROM favitems g join favorites b on b.id_fav=g.id_fav join client c on c.id_cl=b.id_cl where c.id_cl=:uuid",nativeQuery = true)
    List<Favitems> findAllByBasketId(@Param("uuid") UUID basketId);

    //нахождение количества товаров в корзине пользователя
    @Query(value = "SELECT COUNT(g.*) FROM favitems g join favorites b on b.id_fav=g.id_fav join client c on c.id_cl=b.id_cl where c.id_cl=:uuid",nativeQuery = true)
    int findCount(@Param("uuid") UUID uuid);

}
