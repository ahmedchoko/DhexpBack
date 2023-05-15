package com.wevioo.demande.services;


import com.wevioo.demande.entities.Beneficiaire;


public interface BeneficiaireService {
    Beneficiaire createBeneficiaire(Beneficiaire BeneficiaireRequest);

    Beneficiaire getBeneficiaireById(Long id);

    Beneficiaire updateBeneficiaire(Long id, Beneficiaire BeneficiaireRequest);

    Beneficiaire deleteBeneficiaire(Long id);
}
