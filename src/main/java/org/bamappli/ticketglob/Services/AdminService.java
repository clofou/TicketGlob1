package org.bamappli.ticketglob.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Administrateur;
import org.bamappli.ticketglob.Entities.Roles;
import org.bamappli.ticketglob.Repositories.AdministrateurRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Getter
@Setter
@AllArgsConstructor
@Transactional
public class AdminService {
    private AdministrateurRepository adminRepository;
    private ManageAccountService manageAccountService;

    public Administrateur creer(Administrateur admin){

        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        Administrateur ad = (Administrateur) manageAccountService.creerPersonne(admin);

        Roles role1 = new Roles();
        role1.setRole("ADMIN");
        manageAccountService.creerRole(role1);

        Roles role2 = new Roles();
        role2.setRole("FORMATEUR");
        manageAccountService.creerRole(role2);

        Roles role3 = new Roles();
        role3.setRole("APPRENANT");
        manageAccountService.creerRole(role3);

        manageAccountService.AttribuerRoleAPersonne(ad.getUsername(), role1);
        manageAccountService.AttribuerRoleAPersonne(ad.getUsername(), role2);
        manageAccountService.AttribuerRoleAPersonne(ad.getUsername(), role3);

        return ad;
    }

    public List<Administrateur> tout(){
        return adminRepository.findAll();
    }

    public Optional<Administrateur> unAdministrateur(Integer id){
        return adminRepository.findById(id);
    }

    public Administrateur misAjour(Integer id, @NotNull Administrateur updateAdministrateur){
        Optional<Administrateur> existingA = adminRepository.findById(id);
        if(existingA.isPresent()){
            Administrateur existingAdministrateur = existingA.get();
            existingAdministrateur.setEmail(updateAdministrateur.getEmail());
            existingAdministrateur.setNom(updateAdministrateur.getNom());
            existingAdministrateur.setPrenom(updateAdministrateur.getPrenom());
            adminRepository.save(existingAdministrateur);
        }


        return existingA.orElse(null);
    }

    public Administrateur updateAdministrateurPartial(Integer id, Administrateur updatedFields) {
        return adminRepository.findById(id)
                .map(admin -> {
                    if (updatedFields.getNom() != null) {
                        admin.setNom(updatedFields.getNom());
                    }
                    if (updatedFields.getPrenom() != null) {
                        admin.setPrenom(updatedFields.getPrenom());
                    }
                    if (updatedFields.getEmail() != null) {
                        admin.setEmail(updatedFields.getEmail());
                    }
                    return adminRepository.save(admin);
                })
                .orElseThrow(() -> new RuntimeException("Administrateur not found"));
    }

    public void effacer(Integer id){
        adminRepository.deleteById(id);
    }

}
