package com.example.FurnitureStore.Repository;
import com.example.FurnitureStore.Model.Good;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface GoodRepository extends JpaRepository<Good, UUID> {
    //товар по id
    @Query(value = "SELECT g.* FROM Good g join provider p on g.id_prov = p.id_prov WHERE g.id_good = :uuid",nativeQuery = true)
    Good findByUUID(@Param("uuid") UUID uuid);

    //список товаров, на которые есть отзыв у клиента
    @Query(value = "SELECT g.* FROM Good g join provider p on g.id_prov = p.id_prov right join reviews b on b.id_good=g.id_good right join client" +
            " on client.id_cl=reviews.id_cl WHERE client.id_cl = :uuidcl",nativeQuery = true)
    List<Good> findGoodReviewClientByUUID(@Param("uuidcl") UUID uuidcl);

    //список товаров, которые лежат у клиента в корзине
    @Query(value = "SELECT g.* FROM Good g " +
            "JOIN Provider p ON g.id_prov = p.id_prov " +
            "JOIN BasketItems b ON g.id_good = b.id_good " +
            "JOIN Basket ba ON b.id_bas = ba.id_bas " +
            "JOIN Client c ON ba.id_cl= c.id_cl " +
            "WHERE c.id_cl = :uuidcl and ba.status=1", nativeQuery = true)
    List< Good >findGoodBasketClientByUUIDList(@Param("uuidcl") UUID uuidcl);


    //товар которые лежат у клиента в корзине
    @Query(value = "SELECT g.* FROM Good g " +
            "JOIN Provider p ON g.id_prov = p.id_prov " +
            "            JOIN BasketItems b ON g.id_good = b.id_good " +
            "           JOIN Basket ba ON b.id_bas = ba.id_bas " +
            "        JOIN Client c ON ba.id_cl= c.id_cl " +
               "WHERE g.id_good = :uuid AND c.id_cl = :uuidcl",nativeQuery = true)
 Good findGoodBasketClientByUUIDOne(@Param("uuid") UUID uuid,@Param("uuidcl") UUID uuidcl);

    //список товаров, на которые есть отзывы
    @Query(value = "SELECT g.* FROM Good g join provider p on g.id_prov = p.id_prov right join reviews b on b.id_good=g.id_good",nativeQuery = true)
    List<Good> findGoodReviewByUUID();
    @Transactional
    @Modifying
    @Query(value = "Delete c FROM Good c WHERE c.id_good = :id",nativeQuery = true)
    void deleteGood(@Param("id") UUID id);
    @Transactional
    @Modifying
    @Query(value="INSERT INTO Good(id_good, artikul, category, name,description, cost, photo, id_prov, procent, count, rating) " +
            "values(uuid_generate_v4(), :artikul, :category,:name, :description,:cost,:photo,:id_prov,:procent,:count,0)", nativeQuery = true)
    void createGood(@Param("artikul") String artikul, @Param("category") String category, @Param("name") String name, @Param("description") String description
            , @Param("cost") BigDecimal cost, @Param("photo") String photo, @Param("id_prov") UUID id_prov, @Param("procent") int procent, @Param("count") int count);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Good SET name=:name,description=:description,photo=:photo, cost=:cost, procent=:procent, count=:count, rating=:rating WHERE id_good = :id",nativeQuery = true)
    void setUpdatedAtById(@Param("id") UUID id,@Param("name") String name,@Param("description") String description,@Param("photo") String photo
            ,@Param("cost") BigDecimal cost,@Param("procent") int procent,@Param("count") int count,
             @Param("rating") double rating);



    //сортировка
    //по названию
    @Query(value = "SELECT g.* FROM Good g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :category, '%'))", nativeQuery = true)
    List<Good> findAllByName(@Param("category") String category);
    //по цене по убыванию
    @Query(value = "SELECT g.* FROM Good g ORDER BY Cost desc",nativeQuery = true)
    List<Good> findAllByOrderByCostDesc();
    //по цене по возрастанию
    @Query(value = "SELECT g.* FROM Good g ORDER BY Cost asc",nativeQuery = true)
    List<Good> findAllByOrderByCostAsc();
    //по категории
    @Query(value = "SELECT g.* FROM Good g where category=:category",nativeQuery = true)
    List<Good> findAllByCategory(@Param("category") String category);
    //по скидке по возрастанию
    @Query(value = "SELECT g.* FROM Good g where procent between :p1 and :p2 ORDER BY procent asc",nativeQuery = true)
    List<Good> findAllByProcentAsc(@Param("p1") int p1, @Param("p2")  int p2);
    //по скидке по убыванию
    @Query(value = "SELECT g.* FROM Good g where procent between :p1 and :p2 ORDER BY procent desc",nativeQuery = true)
    List<Good> findAllByProcentDesc(@Param("p1") int p1, @Param("p2") int p2);
    //по цене между
    @Query(value = "SELECT g.* FROM Good g where cost between :p1 and :p2",nativeQuery = true)
    List<Good> findAllByCostBetween(@Param("p1") BigDecimal p1, @Param("p2") BigDecimal p2);
    //по поставщику
    @Query(value = "SELECT g.* FROM Good g JOIN provider p on g.id_prov=p.id_prov WHERE p.company = :companyName",nativeQuery = true)
    List<Good> findByProviderCompany(@Param("companyName") String companyName);

    @Query(value = "SELECT g.* FROM Good g where rating >= :p1",nativeQuery = true)
    List<Good> findAllByRating(@Param("p1") double p1);
    @Query(value = "SELECT g.* FROM Good g where rating =0",nativeQuery = true)
    List<Good> findAllByRating();

    @Query(value = "SELECT g.* FROM Good g where count > 0",nativeQuery = true)
    List<Good> findInstock();
}
