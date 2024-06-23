package org.bamappli.ticketglob.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bamappli.ticketglob.Entities.BDC;
import org.bamappli.ticketglob.Repositories.BdcRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
@Transactional
public class BdcService {
    private BdcRepository bdcRepository;
    private ManageAccountService manageAccountService;

    public BDC creer(BDC bdc){
        return bdcRepository.save(bdc);
    }

    public List<BDC> tout(){
        return bdcRepository.findAll();
    }

    public Optional<BDC> unBDC(Integer id){
        return bdcRepository.findById(id);
    }

    public BDC misAjour(Integer id, @NotNull BDC updateBDC){
        Optional<BDC> existingA = bdcRepository.findById(id);
        if(existingA.isPresent()){
            BDC existingBDC = existingA.get();
            existingBDC.setQuestion(updateBDC.getQuestion());
            existingBDC.setReponse(updateBDC.getReponse());
            bdcRepository.save(existingBDC);
        }


        return existingA.orElse(null);
    }

    public BDC updateBDCPartial(Integer id, BDC updatedFields) {
        return bdcRepository.findById(id)
                .map(bdc -> {
                    if (updatedFields.getQuestion() != null) {
                        bdc.setQuestion(updatedFields.getQuestion());
                    }
                    if (updatedFields.getReponse() != null) {
                        bdc.setReponse(updatedFields.getReponse());
                    }
                    return bdcRepository.save(bdc);
                })
                .orElseThrow(() -> new RuntimeException("BDC not found"));
    }

    public void effacer(Integer id){
        bdcRepository.deleteById(id);
    }

}
