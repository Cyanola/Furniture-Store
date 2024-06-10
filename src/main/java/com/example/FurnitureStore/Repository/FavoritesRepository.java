package com.example.FurnitureStore.Repository;
import com.example.FurnitureStore.Model.Favorites;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, UUID> {

    @Query(value = "SELECT g.*  FROM favorites g WHERE g.id_fav = :uuid",nativeQuery = true)
    Favorites findByUUID(@Param("uuid") UUID uuid);
    //для клиента указать его корзину
    @Query(value = "SELECT g.*  FROM favorites g join client c on g.id_cl=c.id_cl WHERE c.id_cl = :uuid",nativeQuery = true)
 List< Favorites> findByClientId(@Param("uuid") UUID clientId);

    @Query(value = "SELECT g.*  FROM favorites g join favitems b  on b.id_fav=g.id_fav join client c on g.id_cl=c.id_cl WHERE c.id_cl = :uuid",nativeQuery = true)
    List< Favorites> findByClientIdxd(@Param("uuid") UUID clientId);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Favorites(id_fav, id_cl) " +
            "values (uuid_generate_v4(), :id_cl)",nativeQuery = true)
    void createFavorite(@Param("id_cl") UUID id_cl);
}
