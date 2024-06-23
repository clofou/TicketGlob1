package org.bamappli.ticketglob.Controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Apprenant;
import org.bamappli.ticketglob.Entities.Enum.Statut;
import org.bamappli.ticketglob.Entities.Formateur;
import org.bamappli.ticketglob.Entities.Reponse;
import org.bamappli.ticketglob.Entities.Ticket;
import org.bamappli.ticketglob.Services.ApprenantService;
import org.bamappli.ticketglob.Services.FormateurService;
import org.bamappli.ticketglob.Services.ReponseService;
import org.bamappli.ticketglob.Services.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formateurs")
@Getter
@Setter
@AllArgsConstructor
public class FormateurController {
    ApprenantService apprenantService;
    ReponseService reponseService;

    // Ajout , suppresion et Modification d'un compte Apprenant
    @PostMapping("/apprenants")
    public Apprenant createApprenant(@RequestBody Apprenant apprenant) {
        return apprenantService.creer(apprenant);
    }

    @GetMapping("/apprenants")
    public List<Apprenant> getAllApprenant() {
        return apprenantService.tout();
    }
    @GetMapping("/apprenants/{id}")
    public Optional<Apprenant> getOneApprenant(@PathVariable Integer id) {
        return apprenantService.unApprenant(id);
    }

    @PutMapping("/apprenants/{id}")
    public Apprenant updateApprenant(@PathVariable Integer id, @RequestBody Apprenant apprenant) {
        return apprenantService.misAjour(id, apprenant);
    }

    @DeleteMapping("/apprenants/{id}")
    public void deleteApprenant(@PathVariable Integer id) {
        apprenantService.effacer(id);
    }

    // Gestion de Reponse au Ticket
    @PostMapping
    public Reponse createReponse(@RequestBody Reponse reponse) {
        return reponseService.creer(reponse);
    }

    @GetMapping
    public List<Reponse> getAll() {
        return reponseService.tout();
    }
    @GetMapping("/{id}")
    public Optional<Reponse> getOne(@PathVariable Integer id) {
        return reponseService.unReponse(id);
    }

    @PutMapping("/{id}")
    public Reponse update(@PathVariable Integer id, @RequestBody Reponse reponse) {
        return reponseService.misAjour(id, reponse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        reponseService.effacer(id);
    }

}
