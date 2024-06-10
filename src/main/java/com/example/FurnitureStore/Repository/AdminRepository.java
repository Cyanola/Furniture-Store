package com.example.FurnitureStore.Repository;

import com.example.FurnitureStore.Model.Admin;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, UUID> {
    @Query(value = "SELECT a FROM Admin a WHERE a.id = :uuid",nativeQuery = true)
  Optional< Admin> findByUUID(@Param("uuid") UUID uuid);


    @Query(value = "SELECT a.* FROM Admin a WHERE a.login = :login and a.password=:password",nativeQuery = true)
 Optional<   Admin >findByLoginPassword(@Param("login") String login,@Param("password") String password);

}
