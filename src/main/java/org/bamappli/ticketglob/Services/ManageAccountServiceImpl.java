package org.bamappli.ticketglob.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.bamappli.ticketglob.Entities.Personne;
import org.bamappli.ticketglob.Entities.Roles;
import org.bamappli.ticketglob.Repositories.PersonneRepository;
import org.bamappli.ticketglob.Repositories.RolesRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class ManageAccountServiceImpl implements ManageAccountService {
    private PersonneRepository personneRepository;
    private RolesRepository rolesRepository;

    @Override
    public Personne creerPersonne(Personne personne) {
        Personne p = personneRepository.findPersonneByUsername(personne.getUsername());
        if (p!=null) throw new RuntimeException("Cet Utilisateur Existe Déjà");
        return personneRepository.save(personne);
    }

    @Override
    public void creerRole(Roles roles) {
        Roles roles1 = rolesRepository.findByName(roles.getRole());
        if (roles1 != null) return;

        rolesRepository.save(roles);
    }

    @Override
    public void AttribuerRoleAPersonne(String username, Roles role) {
        Personne personne = personneRepository.findPersonneByUsername(username);

        personne.getRoles().add(role);
    }

    @Override
    public void SupprimerRoleAPersonne(String username, Roles role) {
        Personne personne = personneRepository.findPersonneByUsername(username);

        personne.getRoles().remove(role);
    }

    @Override
    public Personne loadUserByUsername(String username) {
        return personneRepository.findPersonneByUsername(username);
    }

    @Override
    public Personne getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return personneRepository.findPersonneByUsername(authentication.getName());
    }
}
