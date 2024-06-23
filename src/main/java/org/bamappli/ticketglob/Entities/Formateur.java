package org.bamappli.ticketglob.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Formateur extends Personne implements Serializable {

    @Column(length = 50)
    private String specialite;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Administrateur admin;

    @JsonIgnore
    @OneToMany(mappedBy = "formateur")
    private List<Apprenant> apprenants;

    @JsonIgnore
    @OneToMany(mappedBy = "formateur")
    private List<Reponse> reponses;

    @JsonIgnore
    @OneToMany(mappedBy = "formateur")
    private List<BDC> bdcs;



}
