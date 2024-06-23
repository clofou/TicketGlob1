package org.bamappli.ticketglob.Repositories;

import org.bamappli.ticketglob.Entities.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Integer> {
    @Query("select p.email from Personne p inner join Formateur f on p.id=f.id")
    List<String> recupererMailDeTousLesFormateurs();
}
