package com.example.FurnitureStore.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;
@Entity(name="provider")
@Data
@AllArgsConstructor
@Table(name="provider")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)

public class Provider {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_prov", updatable = false, nullable = false)
    private UUID id;
    @NotNull
    @NotBlank
    @Column(name = "company")
    private String company;
    @NotNull
    @NotBlank
    @Email
    @Column(name = "email")
    private String email;
    @NotNull
    @NotBlank
    @Column(name = "contract")
    private String contract;
    @Column(name = "phone")
    private String phone;
    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "provider")
    private List<Good> goods;

    public Provider() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
    @Transient
    public UUID getUUID() {
        return id;
    }
}
