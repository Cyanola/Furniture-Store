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

import java.util.UUID;
@Entity(name="admin")
@Data
@AllArgsConstructor
@Table(name="admin")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Admin {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @NotNull
    @NotBlank
    @Column(name = "login")
    private String login;
    @NotNull
    @NotBlank
    @Email
    @Column(name = "password")
    private String password;
    public Admin() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
    @Transient
    public UUID getUUID() {

        return id;
    }
}
