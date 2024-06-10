package com.example.FurnitureStore.Repository;
import com.example.FurnitureStore.Model.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Client(id_cl, surname, name, patr,phone, email, photo, password, address, login) " +
            "values(uuid_generate_v4(), :surname, :name,:patr, :phone,:email,:photo,:password,:address,:login)",nativeQuery = true)
    void createClient(@Param("surname") String surname,@Param("name") String name,@Param("patr") String patr,@Param("phone") String phone
    ,@Param("email") String email,@Param("photo") String photo,@Param("password") String password,@Param("address") String address,
                      @Param("login") String login);

    @Transactional
    @Modifying
    @Query(value = "Delete c FROM Client c WHERE c.id_cl = :id",nativeQuery = true)
    void deleteClient(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Client c SET c.surname=:surname, c.name=:name, c.patr=:patr,c.phone=:phone, c.email=:email, c.photo=:photo, c.password=:password, c.address=:address, c.login=:login WHERE c.id_cl = :id",nativeQuery = true)
    void setUpdatedAtById(@Param("id") UUID id,@Param("surname") String surname,@Param("name") String name,@Param("patr") String patr,@Param("phone") String phone
            ,@Param("email") String email,@Param("photo") String photo,@Param("password") String password,@Param("address") String address,
                          @Param("login") String login);


    @Query(value = "SELECT c.* FROM Client c WHERE c.id_cl = :uuid",nativeQuery = true)
    Optional< Client > findByUUID(@Param("uuid") UUID uuid);
    @Query(value = "SELECT a.* FROM Client a WHERE a.login = :login and a.password=:password",nativeQuery = true)
  Optional<Client> findByLoginPassword(@Param("login") String login,@Param("password") String password);


}
