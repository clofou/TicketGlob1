package org.bamappli.ticketglob.Services;

import org.bamappli.ticketglob.Entities.Personne;
import org.bamappli.ticketglob.Entities.Roles;

public interface ManageAccountService {
    public Personne creerPersonne(Personne personne);
    public void creerRole(Roles roles);
    public void AttribuerRoleAPersonne(String username, Roles role);
    public void SupprimerRoleAPersonne(String username, Roles role);
    public Personne loadUserByUsername(String username);
    public Personne getCurrentUser();
}
