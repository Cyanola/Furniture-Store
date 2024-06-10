package com.example.FurnitureStore.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;
@Entity(name="basket")
@Data
@AllArgsConstructor
@Table(name="basket")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Basket {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_bas", updatable = false, nullable = false)
    private UUID id;
    public Basket() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
    @Transient
    public UUID getUUID() {

        return id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_cl")
    protected Client client;


    @Column(name = "status")
    private int status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "basket")
    private List<BasketItems> basketItems;
}
