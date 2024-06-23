package org.bamappli.ticketglob.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Categorie implements Serializable {
    @Id
    private String nom;

    @JsonIgnore
    @OneToMany(mappedBy = "categorie")
    private List<Ticket> tickets;

}
