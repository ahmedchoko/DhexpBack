package com.wevioo.parametrage.servicesImpl;



import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.repository.FondRepository;
import com.wevioo.parametrage.repository.ModaliteRepository;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.services.ModaliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@Service
public class ModaliteServiceImpl implements ModaliteService {
    @Autowired
    private  ModaliteRepository modaliteRepository;
    @Autowired
    private  FondService fondService;
    @Autowired

    private  FondRepository fondRepository;




    @Override
    public Page<Modalite> getAllModalite(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idModalite"));
        Page<Modalite> myDataPage = modaliteRepository.findAll(pageable);
        return myDataPage;
    }

    @Override
    @Transactional
    public Modalite createModalite(ModaliteDto modaliteRequest) {

        Fond fond = fondService.
                getFondById(modaliteRequest
                        .getFond()
                        .getIdFond());

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
    @Override
    public Modalite getModaliteById(Long id) {
        Modalite modalite = modaliteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        return modalite;
    }
    @Override
    @Transactional
    public Modalite updateModalite(Long id, ModaliteDto modaliteRequest) {
        Modalite modalite = modaliteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));

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
    }

    @Override
    public Modalite deleteModalite(Long id) {
        Modalite modalite = modaliteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        modaliteRepository.deleteById(id);
        return modalite;
    }
    @Override
    public List nobreModaliteParType() {
        List<Object[]> resultList = modaliteRepository.findModaliteSummaryByDemandeTypeAndTresorie();
        Map<Object, Map<Object, List<Integer>>> tresorieByDemandeTypeAndMonth = new HashMap<>();
        for (Object[] result : resultList) {
            Object demandeType =result[1];
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
