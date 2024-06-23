package org.bamappli.ticketglob.Repositories;

import org.bamappli.ticketglob.Entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categorie, Integer> {
}
