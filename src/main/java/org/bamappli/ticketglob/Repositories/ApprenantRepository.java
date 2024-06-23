package org.bamappli.ticketglob.Repositories;

import org.bamappli.ticketglob.Entities.Apprenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprenantRepository extends JpaRepository<Apprenant, Integer> {
}
