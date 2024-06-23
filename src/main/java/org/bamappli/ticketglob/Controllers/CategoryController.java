package org.bamappli.ticketglob.Controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Categorie;
import org.bamappli.ticketglob.Services.CategorieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/categorie")
@Getter
@Setter
@AllArgsConstructor
public class CategoryController {
    CategorieService categoryService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Categorie create(@RequestBody Categorie category) {
        return categoryService.creer(category);
    }

    @GetMapping
    public List<Categorie> getAll() {
        return categoryService.tout();
    }
    @GetMapping("/{id}")
    public Optional<Categorie> getOne(@PathVariable Integer id) {
        return categoryService.unCategory(id);
    }

    @PutMapping("/{id}")
    public Categorie update(@PathVariable Integer id, @RequestBody Categorie category) {
        return categoryService.misAjour(id, category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        categoryService.effacer(id);
    }

}
