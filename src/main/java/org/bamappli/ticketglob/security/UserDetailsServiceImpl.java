package org.bamappli.ticketglob.security;

import lombok.AllArgsConstructor;
import org.bamappli.ticketglob.Entities.Personne;
import org.bamappli.ticketglob.Entities.Roles;
import org.bamappli.ticketglob.Services.ManageAccountService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    ManageAccountService manageAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Personne personne = manageAccountService.loadUserByUsername(username);
        if (personne == null) throw new UsernameNotFoundException("Utilisateur n'existe pas");

        return User.
                withUsername(personne.getUsername()).
                password(personne.getPassword()).
                roles(rolestoList(personne.getRoles())).build();
    }

    private String[] rolestoList(List<Roles> roles){
        ArrayList<String> retour = new ArrayList<>();
        for (Roles r: roles){
            retour.add(r.getRole());
        }
        return retour.toArray(String[]::new);
    }
}
