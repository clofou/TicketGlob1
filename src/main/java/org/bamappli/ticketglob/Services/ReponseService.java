package org.bamappli.ticketglob.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Reponse;
import org.bamappli.ticketglob.Entities.Formateur;
import org.bamappli.ticketglob.Models.MailStructure;
import org.bamappli.ticketglob.Repositories.FormateurRepository;
import org.bamappli.ticketglob.Repositories.PersonneRepository;
import org.bamappli.ticketglob.Repositories.ReponseRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
@Transactional
public class ReponseService {
    private final PersonneRepository personneRepository;
    private ReponseRepository reponseRepository;
    private FormateurRepository formateurRepository;
    private ManageAccountService manageAccountService;
    private TicketService ticketService;
    MailService mailService;

    public Reponse creer(Reponse reponse){
        Formateur formateur = (Formateur) manageAccountService.getCurrentUser();
        reponse.setFormateur(formateur);

        String email= personneRepository.getEmailAddressByTicketId(reponse.getTicket().getId());
        MailStructure mailStructure = new MailStructure();
        mailStructure.setSubject("Formateur "+formateur.getNom()+"a repondu a: "+ reponse.getTicket().getTitre()) ;
        mailStructure.setMessage(reponse.getText());
        mailService.sendMail(email, mailStructure,reponse.getTicket().getId());
        ticketService.updateStatut(reponse.getTicket().getId(), 2);

        return reponseRepository.save(reponse);
    }

    public List<Reponse> tout(){
        return reponseRepository.findAll();
    }

    public Optional<Reponse> unReponse(Integer id){
        return reponseRepository.findById(id);
    }

    public Reponse misAjour(Integer id, @NotNull Reponse updateReponse){
        Optional<Reponse> existingA = reponseRepository.findById(id);
        if(existingA.isPresent()){
            Reponse existingReponse = existingA.get();
            existingReponse.setText(updateReponse.getText());
            reponseRepository.save(existingReponse);
        }


        return existingA.orElse(null);
    }

    public Reponse updateReponsePartial(Integer id, Reponse updatedFields) {
        return reponseRepository.findById(id)
                .map(reponse -> {
                    if (updatedFields.getText() != null) {
                        reponse.setText(updatedFields.getText());
                    }
                    return reponseRepository.save(reponse);
                })
                .orElseThrow(() -> new RuntimeException("Reponse not found"));
    }

    public void effacer(Integer id){
        reponseRepository.deleteById(id);
    }

}
