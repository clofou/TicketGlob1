package org.bamappli.ticketglob.Controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.*;
import org.bamappli.ticketglob.Services.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@Getter
@Setter
@AllArgsConstructor
public class AdminController {
    private final BCryptPasswordEncoder passwordEncoder;
    AdminService adminService;
    ApprenantService apprenantService;
    FormateurService formateurService;
    CategorieService categoryService;
    RolesService rolesService;

    // Ajout , suppresion et Modification d'un compte Admin
    @PostMapping
    public Administrateur create(@RequestBody Administrateur admin) {
        return adminService.creer(admin);
    }

    @GetMapping
    public List<Administrateur> getAll() {
        return adminService.tout();
    }
    @GetMapping("/{id}")
    public Optional<Administrateur> getOne(@PathVariable Integer id) {
        return adminService.unAdministrateur(id);
    }

    @PutMapping("/{id}")
    public Administrateur update(@PathVariable Integer id, @RequestBody Administrateur admin) {
        return adminService.misAjour(id, admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        adminService.effacer(id);
    }


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


    // Ajout , suppresion et Modification d'un compte Formateur
    @PostMapping("/formateurs")
    public Formateur createFormateur(@RequestBody Formateur formateur) {
        return formateurService.creer(formateur);
    }

    @GetMapping("/formateurs")
    public List<Formateur> getAllFormateur() {
        return formateurService.tout();
    }
    @GetMapping("/formateurs/{id}")
    public Optional<Formateur> getOneFormateur(@PathVariable Integer id) {
        return formateurService.unFormateur(id);
    }

    @PutMapping("/formateurs/{id}")
    public Formateur updateFormateur(@PathVariable Integer id, @RequestBody Formateur formateur) {
        return formateurService.misAjour(id, formateur);
    }

    @DeleteMapping("/formateurs/{id}")
    public void deleteFormateur(@PathVariable Integer id) {
        formateurService.effacer(id);
    }

    @PatchMapping("/formateurs/{id}")
    public Formateur updateFormateurPartial(@PathVariable Integer id, @RequestBody Formateur formateur) {
        return formateurService.updateFormateurPartial(id, formateur);
    }

    // Gestion des Categories
    @PostMapping("/categories")
    public Categorie createCategorie(@RequestBody Categorie category) {
        return categoryService.creer(category);
    }

    @GetMapping("/categories")
    public List<Categorie> getAllCategory() {
        return categoryService.tout();
    }
    @GetMapping("/categories/{id}")
    public Optional<Categorie> getOneCategory(@PathVariable Integer id) {
        return categoryService.unCategory(id);
    }

    @PutMapping("/categories/{id}")
    public Categorie updateCategory(@PathVariable Integer id, @RequestBody Categorie category) {
        return categoryService.misAjour(id, category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategories(@PathVariable Integer id) {
        categoryService.effacer(id);
    }


    // Creation, mis a jour et suppresion de roles
    @PostMapping("/roles")
    public Roles createRoles(@RequestBody Roles roles) {
        return rolesService.creer(roles);
    }

    @GetMapping("/roles")
    public List<Roles> getAllRoles() {
        return rolesService.tout();
    }
    @GetMapping("/roles/{id}")
    public Optional<Roles> getOneRoles(@PathVariable Integer id) {
        return rolesService.unRoles(id);
    }

    @PutMapping("/roles/{id}")
    public Roles updateRoles(@PathVariable String id, @RequestBody Roles roles) {
        return rolesService.misAjour(id, roles, id);
    }

    @DeleteMapping("/roles/{id}")
    public void deleteRoles(@PathVariable String id) {
        rolesService.effacer(id);
    }

}


