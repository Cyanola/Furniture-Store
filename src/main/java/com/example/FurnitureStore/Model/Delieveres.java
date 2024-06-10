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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
@Entity(name="delieveres")
@Data
@AllArgsConstructor
@Table(name="delieveres")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Delieveres {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_del", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @NotBlank
    @Column(name = "amount")
    private BigDecimal amount;
    @NotNull
    @NotBlank
    @Column(name = "date_", updatable = false)
    private Timestamp date_;

    public Delieveres() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
    @Transient
    public Timestamp _date() {

        return date_;
    }
    @Transient
    public UUID getUUID() {

        return id;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_order")

    protected Orders order;

}
