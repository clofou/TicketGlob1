package org.bamappli.ticketglob.Controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.BDC;
import org.bamappli.ticketglob.Services.BdcService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formateur/bdcs")
@Getter
@Setter
@AllArgsConstructor
public class BdcController {
    BdcService bdcService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public BDC create(@RequestBody BDC bdc) {
        return bdcService.creer(bdc);
    }

    @GetMapping
    public List<BDC> getAll() {
        return bdcService.tout();
    }
    @GetMapping("/{id}")
    public Optional<BDC> getOne(@PathVariable Integer id) {
        return bdcService.unBDC(id);
    }

    @PutMapping("/{id}")
    public BDC update(@PathVariable Integer id, @RequestBody BDC bdc) {
        return bdcService.misAjour(id, bdc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        bdcService.effacer(id);
    }

}
