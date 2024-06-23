package org.bamappli.ticketglob.Repositories;

import org.bamappli.ticketglob.Entities.BDC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BdcRepository extends JpaRepository<BDC, Integer> {
}
