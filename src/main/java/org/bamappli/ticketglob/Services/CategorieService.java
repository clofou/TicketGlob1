package org.bamappli.ticketglob.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.Categorie;
import org.bamappli.ticketglob.Repositories.CategoryRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
@Transactional
public class CategorieService {
    private CategoryRepository categoryRepository;
    private ManageAccountService manageAccountService;

    public Categorie creer(Categorie category){
        return categoryRepository.save(category);
    }

    public List<Categorie> tout(){
        return categoryRepository.findAll();
    }

    public Optional<Categorie> unCategory(Integer id){
        return categoryRepository.findById(id);
    }

    public Categorie misAjour(Integer id, @NotNull Categorie updateCategory){
        Optional<Categorie> existingA = categoryRepository.findById(id);
        if(existingA.isPresent()){
            Categorie existingCategory = existingA.get();
            existingCategory.setNom(updateCategory.getNom());

            categoryRepository.save(existingCategory);
        }


        return existingA.orElse(null);
    }

    public void effacer(Integer id){
        categoryRepository.deleteById(id);
    }

}
