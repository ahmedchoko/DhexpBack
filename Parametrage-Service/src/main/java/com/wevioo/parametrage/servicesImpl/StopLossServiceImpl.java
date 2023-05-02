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
    private PartenaireRepository  partenaireRepository;
    @Autowired
    private StoplossPartenaireRepository stoplossPartenaireRepository ;

    //public List<StopLoss> getAllStopLoss() {
      //  return stopLossRepository.findAll();    }
    @Override
    public Page<StopLoss> getAllStopLoss( int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idSL"));
        Page<StopLoss> myDataPage = stopLossRepository.findAll(pageable);
        return myDataPage;
    }
    @Override
    public StopLoss createStopLoss(StopLossDto stoplossRequest) {
        Fond fond = fondService.
                getFondById(stoplossRequest
                        .getFond()
                        .getIdFond());

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

    @Override
    public StopLoss getStopLossById(Long id) {
        StopLoss stopLoss = stopLossRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        return stopLoss;
    }

    @Override
    public StopLoss updateStopLoss(Long id, StopLossDto stoplossRequest) {
        StopLoss stopLoss = stopLossRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));

        stopLoss.setFond(stoplossRequest.getFond());
        stopLoss.setNomSL(stoplossRequest.getNomSL());
        stopLoss.setTauxSL(stoplossRequest.getTauxSL());
        stopLoss.setDateValiditeSL(stoplossRequest.getDateValiditeSL());
        stopLoss.setDateFinSL(stoplossRequest.getDateFinSL());
        stopLoss.setStatutSL(stoplossRequest.getStatutSL());

        return stopLossRepository.save(stopLoss);
    }

    @Override
    public StopLoss deleteStopLoss(Long id) {
        StopLoss stopLoss = stopLossRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        stopLossRepository.deleteById(id);
        return stopLoss;
    }

    @Override
    public StoplossPartenaire createSLPartenaire(StoplossPartenaireDto stoplossPartenaireRequest) {

        StopLoss stopLoss = stopLossRepository.save(stoplossPartenaireRequest.getStoploss());
        Partenaire partenaire = partenaireRepository.findById(stoplossPartenaireRequest.getPartenaire().getIdPartenaire())
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+" not found"));
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
    @Override
    public Page<StoplossPartenaire> getSLPartenaire( int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idSLPartenaire"));
        Page<StoplossPartenaire> myDataPage = stoplossPartenaireRepository.findAll(pageable);
        return myDataPage;
    }
}
