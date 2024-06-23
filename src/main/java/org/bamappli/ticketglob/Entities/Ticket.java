package org.bamappli.ticketglob.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.bamappli.ticketglob.Entities.Enum.Priorite;
import org.bamappli.ticketglob.Entities.Enum.Statut;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String titre;
    private String description;
    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.ENVOYER;
    @Enumerated(EnumType.STRING)
    private Priorite priorite = Priorite.MOYEN;

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @JsonIgnore
    @OneToMany(mappedBy = "ticket")
    private List<Image> imageList;

    @OneToMany(mappedBy = "ticket")
    private List<Reponse> reponses;



}
