package org.bamappli.ticketglob.Services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Apprenant;
import org.bamappli.ticketglob.Entities.Enum.Statut;
import org.bamappli.ticketglob.Entities.Ticket;
import org.bamappli.ticketglob.Models.MailStructure;
import org.bamappli.ticketglob.Repositories.FormateurRepository;
import org.bamappli.ticketglob.Repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class TicketService {
    private TicketRepository ticketRepository;
    private FormateurRepository formateurRepository;
    private ManageAccountService manageAccountService;
    private MailService mailService;

    public Ticket creer(Ticket ticket){
        Apprenant apprenant = (Apprenant) manageAccountService.getCurrentUser();
        ticket.setApprenant(apprenant);
        Ticket ticket1 = ticketRepository.save(ticket);

        List<String> emailsList= formateurRepository.recupererMailDeTousLesFormateurs();
        MailStructure mailStructure = new MailStructure();
        mailStructure.setSubject("TicketGlob: "+ ticket1.getTitre());
        mailStructure.setMessage("Nouveau Ticket ajouté dans l'application par L'apprenant " + apprenant.getUsername()+"\n"+ticket1.getDescription());

        for (String mail: emailsList){
            System.out.println(mail);
            mailService.sendMail(mail, mailStructure,ticket1.getId());
        }
        return ticket1;
    }

    public List<Ticket> tout(){
        return ticketRepository.findAll();
    }

        public Optional<Ticket> unTicket(Integer id){
        return ticketRepository.findById(id);
    }

    public Ticket misAjour(Integer id, @org.jetbrains.annotations.NotNull Ticket updateTicket){
        Optional<Ticket> existingTick = ticketRepository.findById(id);
        if(existingTick.isPresent()){
            Ticket existingTicket = existingTick.get();
            existingTicket.setTitre(updateTicket.getTitre());
            existingTicket.setDescription(updateTicket.getDescription());
            existingTicket.setApprenant(updateTicket.getApprenant());
            ticketRepository.save(existingTicket);
        }
        return existingTick.orElse(null);
    }

    public void updateTicketPartial(Long id, Ticket updatedFields) {
        ticketRepository.findById(Math.toIntExact(id))
                .map(ticket -> {
                    if (updatedFields.getTitre() != null) ticket.setTitre(updatedFields.getTitre());
                    if (updatedFields.getDescription() != null) ticket.setDescription(updatedFields.getDescription());
                    if (updatedFields.getStatut() != null) ticket.setStatut(updatedFields.getStatut());
                    return ticketRepository.save(ticket);
                })
                .orElseThrow(() -> new RuntimeException("Formateur not found"));
    }

    public void effacer(Integer id){
        ticketRepository.deleteById(id);
    }

    public void updateStatut(Long id, Integer nouveauStatut){
        Ticket ticket = new Ticket();
        if (nouveauStatut == 1) ticket.setStatut(Statut.EN_COURS);
        if (nouveauStatut == 2) ticket.setStatut(Statut.TRAITER);

        updateTicketPartial(id, ticket);
    }

}
