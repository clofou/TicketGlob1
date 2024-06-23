package org.bamappli.ticketglob.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Reponse implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private LocalDateTime date = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @JsonIgnore
    @OneToMany(mappedBy = "reponse")
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;

    @JsonIgnore
    @OneToOne(mappedBy = "reponse")
    private BDC bdc;

}
