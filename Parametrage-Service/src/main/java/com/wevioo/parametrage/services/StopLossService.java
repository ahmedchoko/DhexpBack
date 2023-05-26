package com.wevioo.parametrage.services;

import com.wevioo.parametrage.dto.StopLossDto;
import com.wevioo.parametrage.dto.StoplossPartenaireDto;
import com.wevioo.parametrage.entities.StopLoss;
import com.wevioo.parametrage.entities.StoplossPartenaire;
import org.springframework.data.domain.Page;



public interface StopLossService {
    Page<StopLoss> getAllStopLoss( int page, int size);

    StopLoss createStopLoss(StopLossDto stoplossRequest);

    StopLoss getStopLossById(Long id);

    StopLoss updateStopLoss(Long id, StopLossDto stoplossRequest);

    StopLoss deleteStopLoss(Long id);

    StoplossPartenaire createSLPartenaire(StoplossPartenaireDto stoplossPartenaireRequest);
    StoplossPartenaire updateSLPartenaire(StoplossPartenaireDto slpartenaire);

    StoplossPartenaire supprimerSLPartenaire(StoplossPartenaireDto slpartenaire);
    Page<StoplossPartenaire> getSLPartenaire(int page, int size);
}
