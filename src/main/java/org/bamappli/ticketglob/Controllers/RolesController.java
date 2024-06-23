package org.bamappli.ticketglob.Controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Roles;
import org.bamappli.ticketglob.Services.RolesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/roles")
@Getter
@Setter
@AllArgsConstructor
public class RolesController {
    RolesService rolesService;

    @PostMapping
    public Roles create(@RequestBody Roles roles) {
        return rolesService.creer(roles);
    }

    @GetMapping
    public List<Roles> getAll() {
        return rolesService.tout();
    }
    @GetMapping("/{id}")
    public Optional<Roles> getOne(@PathVariable Integer id) {
        return rolesService.unRoles(id);
    }

    @PutMapping("/{id}")
    public Roles update(@PathVariable String id, @RequestBody Roles roles) {
        return rolesService.misAjour(id, roles, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        rolesService.effacer(id);
    }

}
