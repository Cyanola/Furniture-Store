package com.example.FurnitureStore.Model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;
@Entity(name="favorites")
@Data
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id_fav"
)
@Table(name="favorites")
public class Favorites {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_fav", updatable = false, nullable = false)
    private UUID id_fav;
    public Favorites() {
        if (this.id_fav == null) {
            this.id_fav = UUID.randomUUID();
        }
    }
    @Transient
    public UUID getUUID() {

        return id_fav;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_cl")

    protected Client client;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "favorites")
    private List<Favitems> favitems;
}
