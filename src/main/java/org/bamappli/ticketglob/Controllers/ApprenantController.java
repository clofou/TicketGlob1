package org.bamappli.ticketglob.Controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Apprenant;
import org.bamappli.ticketglob.Services.ApprenantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formateur/apprenants")
@Getter
@Setter
@AllArgsConstructor
public class ApprenantController {
    ApprenantService apprenantService;

    @PostMapping
    public Apprenant create(@RequestBody Apprenant apprenant) {
        return apprenantService.creer(apprenant);
    }

    @GetMapping
    public List<Apprenant> getAll() {
        return apprenantService.tout();
    }
    @GetMapping("/{id}")
    public Optional<Apprenant> getOne(@PathVariable Integer id) {
        return apprenantService.unApprenant(id);
    }

    @PutMapping("/{id}")
    public Apprenant update(@PathVariable Integer id, @RequestBody Apprenant apprenant) {
        return apprenantService.misAjour(id, apprenant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        apprenantService.effacer(id);
    }

}
