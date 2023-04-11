package com.wevioo.parametrage.servicesImpl;

import com.wevioo.parametrage.dto.StopLossDto;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.StopLoss;
import com.wevioo.parametrage.repository.StopLossRepository;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.services.StopLossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class StopLossServiceImpl implements StopLossService {

    @Autowired
    private StopLossRepository stopLossRepository;
    @Autowired
    private FondService fondService;

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
                .fond(stoplossRequest.getFond())
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
}
