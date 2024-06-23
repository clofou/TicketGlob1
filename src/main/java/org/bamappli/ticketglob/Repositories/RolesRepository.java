package org.bamappli.ticketglob.Repositories;

import org.bamappli.ticketglob.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    @Query("select r from Roles r where r.role=:x")
    Roles findByName(@Param("x") String name);

    @Modifying
    @Transactional
    @Query("delete from Roles r where r.role=:x")
    void deleteByRole(@Param("x") String role);
}
