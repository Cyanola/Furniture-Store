package com.example.FurnitureStore.Model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
@Entity(name="favitems")
@Data
@AllArgsConstructor
@Table(name="favitems")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id_fav_item"
//)

public class Favitems {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_fav_item", updatable = false, nullable = false)
    private UUID id_fav_item;
     public Favitems() {
        if (this.id_fav_item == null) {
            this.id_fav_item = UUID.randomUUID();
        }
    }
    @Transient
    public UUID getUUID() {

        return id_fav_item;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_good")

    protected Good good;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_fav")

    protected Favorites favorites;
}
