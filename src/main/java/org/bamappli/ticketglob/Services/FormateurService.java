package org.bamappli.ticketglob.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.*;
import org.bamappli.ticketglob.Entities.Formateur;
import org.bamappli.ticketglob.Repositories.FormateurRepository;
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
public class FormateurService {
    private FormateurRepository formateurRepository;
    private ManageAccountService manageAccountService;

    public Formateur creer(Formateur formateur){
        Administrateur admin = (Administrateur) manageAccountService.getCurrentUser();
        formateur.setAdmin(admin);
        formateur.setPassword(new BCryptPasswordEncoder().encode(formateur.getPassword()));
        Formateur formateur1 = (Formateur) manageAccountService.creerPersonne(formateur);

        Roles role1 = new Roles();
        role1.setRole("FORMATEUR");
        manageAccountService.creerRole(role1);

        Roles role3 = new Roles();
        role3.setRole("APPRENANT");
        manageAccountService.creerRole(role3);

        manageAccountService.AttribuerRoleAPersonne(formateur1.getUsername(), role1);
        manageAccountService.AttribuerRoleAPersonne(formateur1.getUsername(), role3);

        return formateur1;
    }

    public List<Formateur> tout(){
        return formateurRepository.findAll();
    }

    public Optional<Formateur> unFormateur(Integer id){
        return formateurRepository.findById(id);
    }

    public Formateur misAjour(Integer id, @NotNull Formateur updateFormateur){
        Optional<Formateur> existingA = formateurRepository.findById(id);
        if(existingA.isPresent()){
            Formateur existingFormateur = existingA.get();
            existingFormateur.setEmail(updateFormateur.getEmail());
            existingFormateur.setNom(updateFormateur.getNom());
            existingFormateur.setPrenom(updateFormateur.getPrenom());
            existingFormateur.setSpecialite(updateFormateur.getSpecialite());
            existingFormateur.setAdmin(updateFormateur.getAdmin());

            formateurRepository.save(existingFormateur);
        }


        return existingA.orElse(null);
    }

    public Formateur updateFormateurPartial(Integer id, Formateur updatedFields) {
        return formateurRepository.findById(id)
                .map(formateur -> {
                    if (updatedFields.getNom() != null) formateur.setNom(updatedFields.getNom());
                    if (updatedFields.getPrenom() != null) formateur.setPrenom(updatedFields.getPrenom());
                    if (updatedFields.getEmail() != null) formateur.setEmail(updatedFields.getEmail());
                    return formateurRepository.save(formateur);
                })
                .orElseThrow(() -> new RuntimeException("Formateur not found"));
    }

    public void effacer(Integer id){
        formateurRepository.deleteById(id);
    }

}
