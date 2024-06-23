package org.bamappli.ticketglob.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Administrateur;
import org.bamappli.ticketglob.Entities.Apprenant;
import org.bamappli.ticketglob.Entities.Formateur;
import org.bamappli.ticketglob.Entities.Roles;
import org.bamappli.ticketglob.Repositories.ApprenantRepository;
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
public class ApprenantService {
    private ApprenantRepository apprenantRepository;
    private ManageAccountService manageAccountService;

    public Apprenant creer(Apprenant apprenant){
        Formateur formateur = (Formateur) manageAccountService.getCurrentUser();
        apprenant.setFormateur(formateur);
        apprenant.setPassword(new BCryptPasswordEncoder().encode(apprenant.getPassword()));
        Apprenant ap = (Apprenant) manageAccountService.creerPersonne(apprenant);

        Roles role3 = new Roles();
        role3.setRole("APPRENANT");
        manageAccountService.creerRole(role3);
        manageAccountService.AttribuerRoleAPersonne(ap.getUsername(), role3);

        return ap;
    }

    public List<Apprenant> tout(){
        return apprenantRepository.findAll();
    }

    public Optional<Apprenant> unApprenant(Integer id){
        return apprenantRepository.findById(id);
    }

    public Apprenant misAjour(Integer id, @NotNull Apprenant updateApprenant){
        Optional<Apprenant> existingA = apprenantRepository.findById(id);
        if(existingA.isPresent()){
            Apprenant existingApprenant = existingA.get();
            existingApprenant.setEmail(updateApprenant.getEmail());
            existingApprenant.setNom(updateApprenant.getNom());
            existingApprenant.setPrenom(updateApprenant.getPrenom());
            apprenantRepository.save(existingApprenant);
        }


        return existingA.orElse(null);
    }

    public Apprenant updateApprenantPartial(Integer id, Apprenant updatedFields) {
        return apprenantRepository.findById(id)
                .map(apprenant -> {
                    if (updatedFields.getNom() != null) {
                        apprenant.setNom(updatedFields.getNom());
                    }
                    if (updatedFields.getPrenom() != null) {
                        apprenant.setPrenom(updatedFields.getPrenom());
                    }
                    if (updatedFields.getEmail() != null) {
                        apprenant.setEmail(updatedFields.getEmail());
                    }
                    return apprenantRepository.save(apprenant);
                })
                .orElseThrow(() -> new RuntimeException("Apprenant not found"));
    }

    public void effacer(Integer id){
        apprenantRepository.deleteById(id);
    }

}
