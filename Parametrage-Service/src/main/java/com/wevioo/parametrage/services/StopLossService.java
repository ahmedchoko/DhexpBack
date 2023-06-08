package com.wevioo.parametrage.services;

import com.wevioo.parametrage.dto.StopLossDto;
import com.wevioo.parametrage.dto.StoplossPartenaireDto;
import com.wevioo.parametrage.entities.StopLoss;
import com.wevioo.parametrage.entities.StoplossPartenaire;

import java.util.List;

import org.springframework.data.domain.Page;



public interface StopLossService {
    Page<StopLoss> getAllStopLoss( int page, int size) throws Exception;

    StopLoss createStopLoss(StopLossDto stoplossRequest) throws Exception;

    StopLoss getStopLossById(Long id) throws Exception;

    StopLoss updateStopLoss(Long id, StopLossDto stoplossRequest) throws Exception;

    StopLoss deleteStopLoss(Long id) throws Exception;
    List<StoplossPartenaire> getSLPartenaire() throws Exception;
    StoplossPartenaire createSLPartenaire(StoplossPartenaireDto stoplossPartenaireRequest) throws Exception;
    StoplossPartenaire updateSLPartenaire(StoplossPartenaireDto slpartenaire) throws Exception;

    StoplossPartenaire supprimerSLPartenaire(StoplossPartenaireDto slpartenaire) throws Exception;
    Page<StoplossPartenaire> getSLPartenaire(int page, int size) throws Exception;
}
