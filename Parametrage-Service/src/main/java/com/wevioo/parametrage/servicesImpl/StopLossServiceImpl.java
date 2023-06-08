package com.wevioo.parametrage.servicesImpl;

import com.wevioo.parametrage.common.StoplossPartenaireKey;
import com.wevioo.parametrage.dto.StopLossDto;
import com.wevioo.parametrage.dto.StoplossPartenaireDto;
import com.wevioo.parametrage.entities.*;
import com.wevioo.parametrage.repository.FondRepository;
import com.wevioo.parametrage.repository.PartenaireRepository;
import com.wevioo.parametrage.repository.StopLossRepository;
import com.wevioo.parametrage.repository.StoplossPartenaireRepository;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.services.StopLossService;
import org.modelmapper.ModelMapper;
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
import java.util.concurrent.ExecutionException;

@Service
public class StopLossServiceImpl implements StopLossService {

    @Autowired
    private StopLossRepository stopLossRepository;
    
    @Autowired
    private FondRepository fondRepository;
    
    @Autowired
    private PartenaireRepository partenaireRepository;
    
    @Autowired
    private StoplossPartenaireRepository stoplossPartenaireRepository;
    private String erroMsg;
    private ModelMapper modelMapper ;

    void VérifSLDto(StopLossDto stopLossRequest) throws Exception{
        if (stopLossRequest == null) {
            erroMsg ="empty request body";
            throw new Exception(erroMsg) ;
        }
        if((stopLossRequest.getNomSL() == null) || (stopLossRequest.getNomSL() == "")) {
            erroMsg ="empty nomSL field";
            throw new Exception(erroMsg) ;
        }
        if(stopLossRequest.getStatutSL() == null ) {
            erroMsg ="empty statutSL field";
            throw new Exception(erroMsg) ;
        }
        if((stopLossRequest.getTauxSL() == null) || (stopLossRequest.getTauxSL() > 99) ) {
            erroMsg ="empty tauxSL field";
            throw new Exception(erroMsg) ;
        }
        if(stopLossRequest.getDateFinSL() == null) {
            erroMsg ="empty dateFin field";
            throw new Exception(erroMsg) ;
        }
        if(stopLossRequest.getDateValiditeSL() == null) {
            erroMsg ="empty dateValidite field";
            throw new Exception(erroMsg) ;
        }
        if(fondRepository.findById(stopLossRequest.getFond().getIdFond()).isEmpty()) {
            erroMsg ="fond inexistant!";
            throw new Exception(erroMsg) ;
        }
        if(! stopLossRepository.findByNomSL(stopLossRequest.getNomSL()).isEmpty()){
            erroMsg ="Stoploss existant!";
            throw new Exception(erroMsg) ;
        }
        if(! stopLossRepository.findByDateValiditeSL(stopLossRequest.getDateValiditeSL()).isEmpty()){
            erroMsg ="Stoploss existant!";
            throw new Exception(erroMsg) ;
        }
        if(! stopLossRepository.findByDateFinSL(stopLossRequest.getDateFinSL()).isEmpty()){
            erroMsg ="Stoploss existant!";
            throw new Exception(erroMsg) ;
        }

    }
    void vérifSLPart(StoplossPartenaireDto slpartenaireRequest)  throws Exception{

        if( stopLossRepository.findById(slpartenaireRequest.getStoploss().getIdSL()).isPresent()) {
            erroMsg = "Stoploss existant!";
            throw new Exception(erroMsg);
        }
        if (partenaireRepository.findById(slpartenaireRequest.getPartenaire().getIdPartenaire()).isEmpty()){
            erroMsg = "Partenaire inexistant!";
            throw new Exception(erroMsg);
        }
        if( partenaireRepository.findById(slpartenaireRequest.getPartenaire().getIdPartenaire())
                .get().getStoplosses().contains(slpartenaireRequest.getStoploss()) ){
            erroMsg ="stoploss partenaire dupliqué";
            throw new Exception(erroMsg) ;
        }
        if((slpartenaireRequest.getTauxSLPartenaire() == null) || (slpartenaireRequest.getTauxSLPartenaire() > 99)) {
            erroMsg ="empty tauxSLPArtenaire field";
            throw new Exception(erroMsg) ;
        }
        if(slpartenaireRequest.getStatutSLPart() == null) {
            erroMsg ="empty statutSLPArtenaire field";
            throw new Exception(erroMsg) ;
        }
        if(slpartenaireRequest.getDateFinSLPart() == null) {
            erroMsg ="empty DateFin field";
            throw new Exception(erroMsg) ;
        }
        if(slpartenaireRequest.getDateValiditeSLPArt() == null) {
            erroMsg ="empty Date Validité field";
            throw new Exception(erroMsg) ;
        }
        if(slpartenaireRequest.getTypeSLPart() == null) {
            erroMsg ="empty typeSL field";
            throw new Exception(erroMsg) ;
        }

    }
    /**
     * Retrieves all StopLoss entries with pagination.
     *
     * @param page The page number
     * @param size The number of items per page
     * @return A page of StopLoss entries
     */

    @Override
    public Page<StopLoss> getAllStopLoss(int page, int size) throws Exception {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("idSL"));
            Page<StopLoss> myDataPage = stopLossRepository.findAll(pageable);
            return myDataPage;
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Creates a new StopLoss entry.
     *
     * @param stoplossRequest The StopLossDto object containing the details of the StopLoss
     * @return The created StopLoss entry
     */
    @Override
    public StopLoss createStopLoss(StopLossDto stoplossRequest) throws Exception {
        VérifSLDto(stoplossRequest);
        try {
            Fond fond = fondRepository.findById(stoplossRequest.getFond().getIdFond()).get();
            StopLoss stopLoss = StopLoss.builder()
                    .fond(fond)
                    .nomSL(stoplossRequest.getNomSL())
                    .tauxSL(stoplossRequest.getTauxSL())
                    .dateValiditeSL(stoplossRequest.getDateValiditeSL())
                    .dateFinSL(stoplossRequest.getDateFinSL())
                    .statutSL(stoplossRequest.getStatutSL())
                    .build();
            return stopLossRepository.save(stopLoss);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Retrieves a StopLoss entry by its ID.
     *
     * @param id The ID of the StopLoss entry
     * @return The StopLoss entry with the given ID
     * @throws NoSuchElementException if the StopLoss entry is not found
     */
    @Override
    public StopLoss getStopLossById(Long id) throws Exception{
        try {
            StopLoss stopLoss = stopLossRepository.findById(id)
                    .orElseThrow(() -> new Exception("Stoploss inexistant!"));
            return stopLoss;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
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
    public StopLoss updateStopLoss(Long id, StopLossDto stoplossRequest) throws Exception {
        StopLoss stopLoss = stopLossRepository.findById(id)
                .orElseThrow(() -> new Exception("stoploss inexistant!"));
        VérifSLDto(stoplossRequest);
        try {
            stopLoss.setFond(stoplossRequest.getFond());
            stopLoss.setNomSL(stoplossRequest.getNomSL());
            stopLoss.setTauxSL(stoplossRequest.getTauxSL());
            stopLoss.setDateValiditeSL(stoplossRequest.getDateValiditeSL());
            stopLoss.setDateFinSL(stoplossRequest.getDateFinSL());
            stopLoss.setStatutSL(stoplossRequest.getStatutSL());

            return stopLossRepository.save(stopLoss);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Deletes a StopLoss entry.
     *
     * @param id The ID of the StopLoss entry to delete
     * @return The deleted StopLoss entry
     * @throws NoSuchElementException if the StopLoss entry is not found
     */
    @Override
    public StopLoss deleteStopLoss(Long id) throws Exception{
        try {
            StopLoss stopLoss = stopLossRepository.findById(id)
                    .orElseThrow(() -> new Exception("Stoploss inexistant!"));
            stopLossRepository.deleteById(id);
            return stopLoss;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    /**
     * Creates a new StoplossPartenaire entry.
     *
     * @param stoplossPartenaireRequest The StoplossPartenaireDto object containing the details of the StoplossPartenaire
     * @return The created StoplossPartenaire entry
     */
    @Override
    public StoplossPartenaire createSLPartenaire(StoplossPartenaireDto stoplossPartenaireRequest) throws Exception{

        vérifSLPart(stoplossPartenaireRequest);
        StopLossDto stopLoss = modelMapper.map(stoplossPartenaireRequest.getStoploss(), StopLossDto.class );
        VérifSLDto(stopLoss);

        try {
            Partenaire partenaire = stoplossPartenaireRequest.getPartenaire();
            StopLoss stoploss = stoplossPartenaireRequest.getStoploss();
            StoplossPartenaire slPartenaire = new StoplossPartenaire(
                    new StoplossPartenaireKey(stopLoss.getIdSL(), partenaire.getIdPartenaire()),
                    stoplossPartenaireRequest.getPartenaire(),
                    stoploss,
                    partenaire.getDateBlocage(),
                    new Date(),
                    stoplossPartenaireRequest.getTypeSLPart(),
                    stoplossPartenaireRequest.getStatutSLPart(),
                    stoplossPartenaireRequest.getTauxSLPartenaire());
            stoplossPartenaireRepository.save(slPartenaire);
            return slPartenaire;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
    
    /**
     * Deletes a StoplossPartenaire entry.
     *
     * @param slpartenaire The StoplossPartenaireDto object containing the details of the StoplossPartenaire to delete
     * @return The deleted StoplossPartenaire entry
     * @throws NoSuchElementException if the StoplossPartenaire entry is not found
     */
    @Override
    public StoplossPartenaire supprimerSLPartenaire(@RequestBody StoplossPartenaireDto slpartenaire) throws Exception{
        if(! partenaireRepository.existsById(slpartenaire.getPartenaire().getIdPartenaire())) {
            erroMsg = "Partenaire inexistant!";
            throw new Exception(erroMsg);
        }
        if(! stopLossRepository.existsById(slpartenaire.getStoploss().getIdSL())) {
            erroMsg = "Stoploss inexistant!";
            throw new Exception(erroMsg);
        }
        StoplossPartenaireKey id = new StoplossPartenaireKey(slpartenaire.getStoploss().getIdSL(),
                slpartenaire.getPartenaire().getIdPartenaire());
        StoplossPartenaire sl = stoplossPartenaireRepository.findById(id).orElseThrow(() ->
               new Exception("Stoploss Partenaire existant!"));

    try {
        stoplossPartenaireRepository.deleteById(id) ;
        return sl ;
    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }

    }
    
    /**
     * Updates a StoplossPartenaire entry.
     *
     * @param slpartenaire The StoplossPartenaireDto object containing the updated details of the StoplossPartenaire
     * @return The updated StoplossPartenaire entry
     */
    @Override
    public StoplossPartenaire updateSLPartenaire(StoplossPartenaireDto slpartenaire) throws Exception {
        try {
            supprimerSLPartenaire(slpartenaire);
            StoplossPartenaire slPartenaire = createSLPartenaire(slpartenaire);
            return slPartenaire;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Retrieves all StoplossPartenaire entries with pagination.
     *
     * @param page The page number
     * @param size The number of items per page
     * @return A page of StoplossPartenaire entries
     */
    @Override
    public Page<StoplossPartenaire> getSLPartenaire(int page, int size) throws Exception{
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("idSLPartenaire"));
            Page<StoplossPartenaire> myDataPage = stoplossPartenaireRepository.findAll(pageable);
            return myDataPage;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Retrieves all StoplossPartenaire entries.
     *
     * @return A list of StoplossPartenaire entries
     */
    @Override
    public List<StoplossPartenaire> getSLPartenaire() throws Exception{
        try {
            return stoplossPartenaireRepository.findAll();
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
