package com.example.FurnitureStore.Model;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Entity(name="good")
@Data
@AllArgsConstructor
@Table(name="good")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
public class Good {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_good", updatable = false, nullable = false)
    private UUID id;
    @NotNull
    @NotBlank
    @Column(name = "artikul")
    private String artikul;
    @NotNull
    @NotBlank

    @Column(name = "category")
    private String category;
    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;
    @NotNull
    @NotBlank
    @Column(name = "description")
    private String description;

    @NotNull
    @NotBlank
    @Column(name = "cost")
    private BigDecimal cost;
    @NotNull
    @NotBlank
    @Column(name = "photo")
    private String photo;
    @NotNull
    @NotBlank
    @Column(name = "procent")
    private int procent;

    @NotNull
    @NotBlank
    @Column(name = "count")
    private int count;

    @Column(name = "rating")
    private double rating;
    public Good() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
    @Transient
    public UUID getUUID() {

        return id;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_prov")

    protected Provider provider;
    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "good")
    private List<Favitems> favitems;
    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "good")
    private List<BasketItems> basketItems;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "good")
    private List<Reviews> reviews;
}
