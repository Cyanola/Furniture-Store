package com.example.FurnitureStore.Model;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;
@Entity(name="basketitems")
@Data
@AllArgsConstructor
@Table(name="basketitems")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
public class BasketItems {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_basket_item", updatable = false, nullable = false)
    private UUID id;
    @NotNull
    @NotBlank
    @Column(name = "quantity")
    private int quantity;

    @NotNull
    @NotBlank
    @Column(name = "amount")
    private BigDecimal amount;
    public BasketItems() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
    @Transient
    public UUID getUUID() {

        return id;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_bas")

    protected Basket basket;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_good")

    protected Good good;
}
