package com.wevioo.parametrage.services;

import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.dto.StopLossDto;
import com.wevioo.parametrage.dto.StoplossPartenaireDto;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.StopLoss;
import com.wevioo.parametrage.entities.StoplossPartenaire;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StopLossService {
    Page<StopLoss> getAllStopLoss( int page, int size);

    StopLoss createStopLoss(StopLossDto stoplossRequest);

    StopLoss getStopLossById(Long id);

    StopLoss updateStopLoss(Long id, StopLossDto stoplossRequest);

    StopLoss deleteStopLoss(Long id);

    StoplossPartenaire createSLPartenaire(StoplossPartenaireDto stoplossPartenaireRequest);
    Page<StoplossPartenaire> getSLPartenaire(int page, int size);
}
