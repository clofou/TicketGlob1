package org.bamappli.ticketglob.Controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Reponse;
import org.bamappli.ticketglob.Services.ReponseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formateur/reponses")
@Getter
@Setter
@AllArgsConstructor
public class ReponseController {
    ReponseService reponseService;

    @PostMapping
    public Reponse create(@RequestBody Reponse reponse) {
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
