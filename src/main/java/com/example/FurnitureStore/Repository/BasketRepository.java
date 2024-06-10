package com.example.FurnitureStore.Repository;
import com.example.FurnitureStore.Model.Basket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BasketRepository extends JpaRepository<Basket, UUID> {
    //для клиента указать его корзину
    @Query(value = "SELECT g.*  FROM Basket g join basketitems b on b.id_bas=g.id_bas join good y on y.id_good=b.id_good " +
            "        join client c on c.id_cl=g.id_cl WHERE g.id_bas = :uuid",nativeQuery = true)
    Optional<Basket> findByUUID(@Param("uuid") UUID uuid);
    @Query(value = "SELECT g.*  FROM Basket g join basketitems b on b.id_bas=g.id_bas join good y on y.id_good=b.id_good " +
            "       join client c on c.id_cl=g.id_cl WHERE c.id_cl = :uuid",nativeQuery = true)
    List<  Basket> findByClientId(@Param("uuid") UUID clientId);
    //корзина
    @Query(value = "SELECT g.*  FROM Basket g join basketitems b on b.id_bas=g.id_bas join good y on y.id_good=b.id_good  " +
            "join client c on c.id_cl=g.id_cl WHERE c.id_cl = :uuidcl and y.id_good=:uuid", nativeQuery = true)
   Optional< Basket > findByUUIDGood(@Param("uuid") UUID uuid,@Param("uuidcl") UUID uuidcl);

    @Query(value = "SELECT g.*  FROM Basket g   " +
            "join client c on c.id_cl=g.id_cl WHERE c.id_cl = :uuidcl and g.status=:status", nativeQuery = true)
 List<Basket> findByStatus(@Param("uuidcl") UUID uuidcl,@Param("status") int status);

    @Query(value = "SELECT g.*  FROM Basket g join basketitems b on b.id_bas=g.id_bas join good y on y.id_good=b.id_good  " +
            "join client c on c.id_cl=g.id_cl WHERE c.id_cl = :uuidcl and g.status=:status", nativeQuery = true)
    List<Basket> findByStatusxd(@Param("uuidcl") UUID uuidcl,@Param("status") int status);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Basket SET status=:status WHERE id_cl=:id",nativeQuery = true)
    void updateBasket(int status, UUID id);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Basket(id_bas, id_cl,status) " +
            "values (uuid_generate_v4(), :id_cl,1)",nativeQuery = true)
  void createBasket(@Param("id_cl") UUID id_cl);


}
