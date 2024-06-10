package com.example.FurnitureStore.Model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@Entity(name="client")
@Data
@AllArgsConstructor
@Table(name="client")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Client {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_cl", updatable = false, nullable = false)
    private UUID id;
    @NotNull
    @NotBlank
    @Column(name = "surname")
    private String surname;
    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;


    @NotNull
    @Column(name = "patr")
    private String patr;
    @NotNull
    @NotBlank
    @Column(name = "phone")
    private String phone;
    @NotNull
    @NotBlank
    @Email
    @Column(name = "email")
    private String email;
    @NotNull
    @NotBlank
    @Column(name = "photo")
    private String photo;
    @NotNull
    @NotBlank
    @Column(name = "password")
    private String password;
    @NotNull
    @NotBlank
    @Column(name = "address")
    private String address;
    @NotNull
    @NotBlank
    @Column(name = "login")
    private String login;

    public Client() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    public Client(UUID userId) {
        this.id=userId;
    }

    @Transient
    public UUID getUUID() {

        return id;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "client")
    private List<Reviews> reviews;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "client")
    private List<Orders> orders;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "client")
    private List<Favorites> favorites;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "client")
    private List<Basket> basket;
}
