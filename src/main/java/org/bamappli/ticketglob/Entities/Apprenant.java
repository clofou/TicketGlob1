package org.bamappli.ticketglob.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Apprenant extends Personne implements Serializable {
    @JsonIgnore
    @OneToMany(mappedBy = "apprenant")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;
}
