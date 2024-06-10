package com.example.FurnitureStore.Repository;
import com.example.FurnitureStore.Model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, UUID>{
    //поставщик
    @Query(value = "SELECT p.* FROM Provider p WHERE p.id_prov = :uuid",nativeQuery = true)
    Provider findByUUID(@Param("uuid") UUID uuid);
    //список поставщиков и товаров, которые они поставили
    @Transactional
    @Modifying
    @Query(value="INSERT INTO Provider(id_prov, company, email, contract,phone) " +
            "values(uuid_generate_v4(), :company,:email, :contract,:phone),", nativeQuery = true)
    void createProvider(@Param("company") String company,@Param("email") String email,@Param("contract") String contract,@Param("phone") String phone);

    @Transactional
    @Modifying
    @Query(value = "Delete c FROM Provider c WHERE c.id_prov = :id",nativeQuery = true)
    void deleteProvider(@Param("id") UUID id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Provider c SET c.company=:company, c.email=:email, c.phone=:phone",nativeQuery = true)
    void setUpdatedAtById(@Param("company") String company,@Param("email") String email,@Param("phone") String phone);



}
