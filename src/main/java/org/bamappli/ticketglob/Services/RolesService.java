package org.bamappli.ticketglob.Services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Roles;
import org.bamappli.ticketglob.Repositories.RolesRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class RolesService {
    private RolesRepository roleRepository;

    public Roles creer(Roles role){
        return roleRepository.save(role);
    }

    public List<Roles> tout(){
        return roleRepository.findAll();
    }

    public Optional<Roles> unRoles(Integer id){
        return roleRepository.findById(id);
    }

    public Roles misAjour(String id, @NotNull Roles updateRoles, String oldRoleName){
        // Delete the old role
        roleRepository.deleteByRole(oldRoleName);

        // Create a new role
        Roles newRole = new Roles();
        newRole.setRole(updateRoles.getRole());



        return roleRepository.save(newRole);
    }

    public Roles updateRolesPartial(Integer id, Roles updatedFields) {
        return roleRepository.findById(id)
                .map(role -> {
                    if (updatedFields.getRole() != null) {
                        role.setRole(updatedFields.getRole());
                    }
                    return roleRepository.save(role);
                })
                .orElseThrow(() -> new RuntimeException("Roles not found"));
    }

    public void effacer(String id){
        roleRepository.deleteByRole(id);
    }

}
