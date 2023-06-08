package com.wevioo.parametrage.servicesImpl;

import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.enums.TypeModalite;
import com.wevioo.parametrage.repository.FondRepository;
import com.wevioo.parametrage.repository.ModaliteRepository;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.services.ModaliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Implémentation du service pour la gestion des modalités.
 */
@Service
public class ModaliteServiceImpl implements ModaliteService {

    private String erroMsg;
    @Autowired
    private ModaliteRepository modaliteRepository;


    @Autowired
    private FondRepository fondRepository;

    @Override
    public void Vérif(ModaliteDto modaliteRequest) throws Exception {
        if (modaliteRequest == null) {
            erroMsg ="empty request body";
            throw new Exception(erroMsg) ;
        }
        if((modaliteRequest.getAbrevModalite() == null) || (modaliteRequest.getAbrevModalite() == "")) {
            erroMsg ="empty abréviation field";
            throw new Exception(erroMsg) ;
        }
        if(modaliteRequest.getStatut() == null){
            erroMsg ="empty statut field";
            throw new Exception(erroMsg) ;
        }
        if((modaliteRequest.getMontantMax() == null) || (String.valueOf(modaliteRequest.getMontantMax()).length() > 9)){
            erroMsg ="empty montantMax field";
            throw new Exception(erroMsg) ;
        }
        if((modaliteRequest.getMontantMin() == null) || (String.valueOf(modaliteRequest.getMontantMin()).length() > 9)){
            erroMsg ="empty montantMin field";
            throw new Exception(erroMsg) ;
        }

        if((modaliteRequest.getTypeModalite() == null)  ){
            erroMsg ="empty or invalid type modalité field";
            throw new Exception(erroMsg) ;
        }
      /*  if (! Arrays.asList(TypeModalite.values()).contains(modaliteRequest.getTypeModalite())) {
            erroMsg ="invalid value for type modalité field";
            throw new Exception(erroMsg);
        }*/
        if(modaliteRequest.getNatureDemande() == null){
            erroMsg ="empty nature demande field";
            throw new Exception(erroMsg) ;
        }
        if( (! modaliteRepository.findByAbrevModalite(modaliteRequest.getAbrevModalite()).isEmpty())
                || (! modaliteRepository.findByNomArabeModalite(modaliteRequest.getNomArabeModalite()).isEmpty())
                || (! modaliteRepository.findByNomCompletModalite(modaliteRequest.getNomCompletModalite()).isEmpty()) ){
            erroMsg ="modalité existante";
            throw new Exception(erroMsg) ;
        }
        if( fondRepository.findById(modaliteRequest.getFond().getIdFond()).isEmpty()) {
            erroMsg ="fond inexistant";
            throw new Exception(erroMsg) ;
        }
        if (! fondRepository.findByModalites(modaliteRepository.findById(modaliteRequest.getIdModalite()).get()).isEmpty()) {
            erroMsg = "Modalité du fond dupliquée!";
            throw new Exception(erroMsg);
        }
    }


    /**
     * Avoir la liste de toutes les modalités avec pagination.
     *
     * @param page Le numéro de page.
     * @param size Le nombre d'éléments par page.
     * @return Page des modalités.
     */
    @Override
    public Page<Modalite> getAllModalite(int page, int size) throws Exception{
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("idModalite"));
            return modaliteRepository.findAll(pageable);
        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Créer une nouvelle modalité.
     *
     * @param modaliteRequest Les informations de la modalité à créer.
     * @return La modalité créée.
     */
    @Override
    @Transactional
    public Modalite createModalite(ModaliteDto modaliteRequest) throws Exception {
        Vérif(modaliteRequest);
        try {
           Fond fond = fondRepository.findById(modaliteRequest.getFond().getIdFond()).get();
                Modalite modalite = Modalite.builder()
                        .nomCompletModalite(modaliteRequest.getNomCompletModalite())
                        .nomArabeModalite(modaliteRequest.getNomArabeModalite())
                        .abrevModalite(modaliteRequest.getAbrevModalite())
                        .fond(fond)
                        .typeModalite(modaliteRequest.getTypeModalite())
                        .statut(modaliteRequest.getStatut())
                        .montantMin(modaliteRequest.getMontantMin())
                        .montantMax(modaliteRequest.getMontantMax())
                        .natureDemande(modaliteRequest.getNatureDemande())
                        .build();
                return modaliteRepository.save(modalite);
            }
            catch (Exception e) {
                erroMsg = "Error in resource creation: verify fields!";
            throw new Exception(e.getMessage());
            }

    }

    /**
     * Obtenir une modalité par son ID.
     *
     * @param id L'ID de la modalité.
     * @return La modalité correspondante.
     * @throws NoSuchElementException si la modalité n'existe pas.
     */
    @Override
    public Modalite getModaliteById(Long id) throws Exception{
        try{
            return modaliteRepository.findById(id).get();
        }
        catch(Exception e) {
            erroMsg = "modalité inexistante!";
            throw new Exception(erroMsg);
        }
    }

    /**
     * Mettre à jour une modalité existante.
     *
     * @param id               L'ID de la modalité à mettre à jour.
     * @param modaliteRequest Les nouvelles informations de la modalité.
     * @return La modalité mise à jour.
     * @throws NoSuchElementException si la modalité n'existe pas.
     */
    @Override
    @Transactional
    public Modalite updateModalite(Long id, ModaliteDto modaliteRequest) throws Exception{
        Modalite modalite = modaliteRepository.findById(id)
                .orElseThrow(() -> new Exception("Modalité inexistante!"));

        Vérif(modaliteRequest);
        try {
            modalite.setNomCompletModalite(modaliteRequest.getNomCompletModalite());
            modalite.setNomArabeModalite(modaliteRequest.getNomArabeModalite());
            modalite.setAbrevModalite(modaliteRequest.getAbrevModalite());
            modalite.setFond(modaliteRequest.getFond());
            modalite.setTypeModalite(modaliteRequest.getTypeModalite());
            modalite.setStatut(modaliteRequest.getStatut());
            modalite.setMontantMin(modaliteRequest.getMontantMin());
            modalite.setMontantMax(modaliteRequest.getMontantMax());
            modalite.setNatureDemande(modaliteRequest.getNatureDemande());
            return modaliteRepository.save(modalite);
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Supprimer une modalité par son ID.
     *
     * @param id L'ID de la modalité à supprimer.
     * @return La modalité supprimée.
     * @throws NoSuchElementException si la modalité n'existe pas.
     */
    @Override
    public Modalite deleteModalite(Long id) throws Exception {
        try {
            Modalite modalite = modaliteRepository.findById(id)
                    .orElseThrow(() -> new Exception("modalité inexistante!"));
            modaliteRepository.deleteById(id);
            return modalite;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Obtenir le nombre de modalités par type de demande et par trésorerie.
     *
     * @return Les données contenant le nombre de modalités par type de demande et par trésorerie.
     */
    @Override
    public List nobreModaliteParType() {
        List<Object[]> resultList = modaliteRepository.findModaliteSummaryByDemandeTypeAndTresorie();
        Map<Object, Map<Object, List<Integer>>> tresorieByDemandeTypeAndMonth = new HashMap<>();
        for (Object[] result : resultList) {
            Object demandeType = result[1];
            Integer tresorie = ((Number) result[2]).intValue();
            Object month = result[0];
            if (tresorieByDemandeTypeAndMonth.containsKey(month)) {
                Map<Object, List<Integer>> demandesMap = tresorieByDemandeTypeAndMonth.get(month);
                if (demandesMap.containsKey(demandeType)) {
                    demandesMap.get(demandeType).add(tresorie);
                } else {
                    List<Integer> tresorieList = new ArrayList<>();
                    tresorieList.add(tresorie);
                    demandesMap.put(demandeType, tresorieList);
                }
            } else {
                Map<Object, List<Integer>> demandesMap = new HashMap<>();
                List<Integer> tresorieList = new ArrayList<>();
                tresorieList.add(tresorie);
                demandesMap.put(demandeType, tresorieList);
                tresorieByDemandeTypeAndMonth.put(month, demandesMap);
            }
        }
        List datasend = new ArrayList();
        datasend.add(tresorieByDemandeTypeAndMonth);
        return datasend;
    }
}
