package com.wevioo.demande.services;

import com.wevioo.demande.entities.MontantInvestissement;

public interface MontantInvestissementService {
    MontantInvestissement createMontantInvestissement(MontantInvestissement MontantInvestissementRequest);

    MontantInvestissement getMontantInvestissementById(Long id);

    MontantInvestissement updateMontantInvestissement(Long id, MontantInvestissement MontantInvestissementRequest);

    MontantInvestissement deleteMontantInvestissement(Long id);
}
