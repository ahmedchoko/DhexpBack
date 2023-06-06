package com.wevioo.parametrage.servicesImpl;

import com.wevioo.parametrage.common.StoplossPartenaireKey;
import com.wevioo.parametrage.dto.StopLossDto;
import com.wevioo.parametrage.dto.StoplossPartenaireDto;
import com.wevioo.parametrage.entities.*;
import com.wevioo.parametrage.repository.PartenaireRepository;
import com.wevioo.parametrage.repository.StopLossRepository;
import com.wevioo.parametrage.repository.StoplossPartenaireRepository;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.services.StopLossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StopLossServiceImpl implements StopLossService {

    @Autowired
    private StopLossRepository stopLossRepository;
    
    @Autowired
    private FondService fondService;
    
    @Autowired
    private PartenaireRepository partenaireRepository;
    
    @Autowired
    private StoplossPartenaireRepository stoplossPartenaireRepository;

    /**
     * Retrieves all StopLoss entries with pagination.
     *
     * @param page The page number
     * @param size The number of items per page
     * @return A page of StopLoss entries
     */
    @Override
    public Page<StopLoss> getAllStopLoss(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idSL"));
        Page<StopLoss> myDataPage = stopLossRepository.findAll(pageable);
        return myDataPage;
    }
    
    /**
     * Creates a new StopLoss entry.
     *
     * @param stoplossRequest The StopLossDto object containing the details of the StopLoss
     * @return The created StopLoss entry
     */
    @Override
    public StopLoss createStopLoss(StopLossDto stoplossRequest) {
        Fond fond = fondService.getFondById(stoplossRequest.getFond().getIdFond());

        StopLoss stopLoss = StopLoss.builder()
                .fond(fond)
                .nomSL(stoplossRequest.getNomSL())
                .tauxSL(stoplossRequest.getTauxSL())
                .dateValiditeSL(stoplossRequest.getDateValiditeSL())
                .dateFinSL(stoplossRequest.getDateFinSL())
                .statutSL(stoplossRequest.getStatutSL())
                .build();
        return stopLossRepository.save(stopLoss);
    }

    /**
     * Retrieves a StopLoss entry by its ID.
     *
     * @param id The ID of the StopLoss entry
     * @return The StopLoss entry with the given ID
     * @throws NoSuchElementException if the StopLoss entry is not found
     */
    @Override
    public StopLoss getStopLossById(Long id) {
        StopLoss stopLoss = stopLossRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id " + id + " not found"));
        return stopLoss;
    }

    /**
     * Updates a StopLoss entry.
     *
     * @param id The ID of the StopLoss entry to update
     * @param stoplossRequest The StopLossDto object containing the updated details of the StopLoss
     * @return The updated StopLoss entry
     * @throws NoSuchElementException if the StopLoss entry is not found
     */
    @Override
    public StopLoss updateStopLoss(Long id, StopLossDto stoplossRequest) {
        StopLoss stopLoss = stopLossRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id " + id + " not found"));

        stopLoss.setFond(stoplossRequest.getFond());
        stopLoss.setNomSL(stoplossRequest.getNomSL());
        stopLoss.setTauxSL(stoplossRequest.getTauxSL());
        stopLoss.setDateValiditeSL(stoplossRequest.getDateValiditeSL());
        stopLoss.setDateFinSL(stoplossRequest.getDateFinSL());
        stopLoss.setStatutSL(stoplossRequest.getStatutSL());

        return stopLossRepository.save(stopLoss);
    }

    /**
     * Deletes a StopLoss entry.
     *
     * @param id The ID of the StopLoss entry to delete
     * @return The deleted StopLoss entry
     * @throws NoSuchElementException if the StopLoss entry is not found
     */
    @Override
    public StopLoss deleteStopLoss(Long id) {
        StopLoss stopLoss = stopLossRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id " + id + " not found"));
        stopLossRepository.deleteById(id);
        return stopLoss;
    }

    /**
     * Creates a new StoplossPartenaire entry.
     *
     * @param stoplossPartenaireRequest The StoplossPartenaireDto object containing the details of the StoplossPartenaire
     * @return The created StoplossPartenaire entry
     */
    @Override
    public StoplossPartenaire createSLPartenaire(StoplossPartenaireDto stoplossPartenaireRequest) {
        StopLoss stopLoss = stopLossRepository.save(stoplossPartenaireRequest.getStoploss());
        Partenaire partenaire = partenaireRepository.findById(stoplossPartenaireRequest.getPartenaire().getIdPartenaire())
                .orElseThrow(() -> new NoSuchElementException("Resource with id not found"));

        StoplossPartenaire slPartenaire = new StoplossPartenaire(
                new StoplossPartenaireKey(stopLoss.getIdSL(), partenaire.getIdPartenaire()),
                stoplossPartenaireRequest.getPartenaire(),
                stopLoss,
                partenaire.getDateBlocage(),
                new Date(),
                stoplossPartenaireRequest.getTypeSLPart(),
                stoplossPartenaireRequest.getStatutSLPart(),
                stoplossPartenaireRequest.getTauxSLPartenaire());
        System.out.println(slPartenaire);
        stoplossPartenaireRepository.save(slPartenaire);
        return slPartenaire;
    }
    
    /**
     * Deletes a StoplossPartenaire entry.
     *
     * @param slpartenaire The StoplossPartenaireDto object containing the details of the StoplossPartenaire to delete
     * @return The deleted StoplossPartenaire entry
     * @throws NoSuchElementException if the StoplossPartenaire entry is not found
     */
    @Override
    public StoplossPartenaire supprimerSLPartenaire(@RequestBody StoplossPartenaireDto slpartenaire) {
        StoplossPartenaireKey id = new StoplossPartenaireKey(slpartenaire.getStoploss().getIdSL(),
                slpartenaire.getPartenaire().getIdPartenaire());
        StoplossPartenaire stoplossPartenaire = stoplossPartenaireRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id " + id + " not found"));

        stoplossPartenaireRepository.deleteById(id);
        return stoplossPartenaire;
    }
    
    /**
     * Updates a StoplossPartenaire entry.
     *
     * @param slpartenaire The StoplossPartenaireDto object containing the updated details of the StoplossPartenaire
     * @return The updated StoplossPartenaire entry
     */
    @Override
    public StoplossPartenaire updateSLPartenaire(StoplossPartenaireDto slpartenaire) {

        supprimerSLPartenaire(slpartenaire);
        StoplossPartenaire slPartenaire = createSLPartenaire(slpartenaire);

        return slPartenaire;
    }

    /**
     * Retrieves all StoplossPartenaire entries with pagination.
     *
     * @param page The page number
     * @param size The number of items per page
     * @return A page of StoplossPartenaire entries
     */
    @Override
    public Page<StoplossPartenaire> getSLPartenaire(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idSLPartenaire"));
        Page<StoplossPartenaire> myDataPage = stoplossPartenaireRepository.findAll(pageable);
        return myDataPage;
    }
    
    /**
     * Retrieves all StoplossPartenaire entries.
     *
     * @return A list of StoplossPartenaire entries
     */
    @Override
    public List<StoplossPartenaire> getSLPartenaire() {
        return stoplossPartenaireRepository.findAll();
    }
}
