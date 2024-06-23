package org.bamappli.ticketglob.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class BDC implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question;

    @OneToOne
    @JoinColumn(name = "reponse_id")
    private Reponse reponse;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;
}
