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

import java.util.UUID;
@Entity(name="reviews")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id_review"
)
@Data
@AllArgsConstructor
@Table(name="reviews")

public class Reviews {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_review", updatable = false, nullable = false)
    private UUID id_review;
    @NotNull
    @NotBlank
    @Column(name = "mark")
    private int mark;

    @NotNull
    @NotBlank
    @Column(name = "review")
    private String review;

    @Transient
    public UUID getUUID() {

        return id_review;
    }
    public Reviews() {
        if (this.id_review == null) {
            this.id_review = UUID.randomUUID();
        }
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_cl")

    protected Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_good")

    protected Good good;
}
