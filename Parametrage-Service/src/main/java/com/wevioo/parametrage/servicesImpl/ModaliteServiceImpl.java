package com.wevioo.parametrage.servicesImpl;


import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.repository.ModaliteRepository;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.services.ModaliteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ModaliteServiceImpl implements ModaliteService {
    private final ModaliteRepository modaliteRepository;
    private final FondService fondService;

    public ModaliteServiceImpl(ModaliteRepository modaliteRepository, FondService fondService) {
        super();
        this.modaliteRepository = modaliteRepository;
        this.fondService = fondService;
    }


    @Override
    public List<Modalite> getAllModalite() {
        return modaliteRepository.findAll();
    }

    @Override
    public Modalite createModalite(ModaliteDto modaliteRequest) {

            Fond fond = fondService.getFondById(modaliteRequest.getFond().getIdFond());
            Modalite modalite = Modalite.builder()
                    .nomCompletModalite(modaliteRequest.getNomCompletMod())
                    .nomArabeModalite(modaliteRequest.getNomArabeMod())
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
        System.out.println("retrieved modalite object\n"+ modalite);
        modalite.setNomCompletModalite(modaliteRequest.getNomCompletMod());
        modalite.setNomArabeModalite(modaliteRequest.getNomArabeMod());
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

}
