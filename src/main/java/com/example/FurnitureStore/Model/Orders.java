package com.example.FurnitureStore.Model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
@Entity(name="orders")
@Data
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id_order"
)
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_order", updatable = false, nullable = false)
    private UUID id_order;


    @NotNull
    @NotBlank
    @Column(name = "status")
    private String  status;
    @NotNull
    @NotBlank
    @Column(name = "date_oform")
    private Timestamp date_oform;



    public Orders() {
        if (this.id_order == null) {
            this.id_order = UUID.randomUUID();
        }
    }
    @Transient
    public Timestamp dateofrom() {

        return date_oform;
    }


    @Transient
    public void setdateofrom(Timestamp value) {

   date_oform = value;
    }

    @Transient
    public UUID getUUID() {

        return id_order;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_bas")

    protected Basket basket;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_cl")

    protected Client client;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order")
    private List<Delieveres> delieveres;

}
