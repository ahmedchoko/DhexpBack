package com.wevioo.demande.servicesImpl;

import com.wevioo.demande.entities.Beneficiaire;
import com.wevioo.demande.repository.BeneficiaireRepository;
import com.wevioo.demande.services.BeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service
public class BeneficiaireServiceImpl implements BeneficiaireService {
@Autowired
    BeneficiaireRepository beneficiaireRepository;
    @Override
    public Beneficiaire createBeneficiaire(Beneficiaire BeneficiaireRequest) {
        Beneficiaire beneficiaire = Beneficiaire.builder()
                .activite(BeneficiaireRequest.getActivite())
                .natureActivite(BeneficiaireRequest.getNatureActivite())
                .adresse(BeneficiaireRequest.getAdresse())
                .codePostal(BeneficiaireRequest.getCodePostal())
                .region(BeneficiaireRequest.getRegion())
                .typPersonne(BeneficiaireRequest.getTypPersonne())
                .build();
        return beneficiaireRepository.save(beneficiaire);
    }

    @Override
    public Beneficiaire getBeneficiaireById(Long id) {
        Beneficiaire beneficiaire = beneficiaireRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        return beneficiaire;
    }

    @Override
    public Beneficiaire updateBeneficiaire(Long id, Beneficiaire BeneficiaireRequest) {
        Beneficiaire beneficiaire = beneficiaireRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        beneficiaire.setActivite(BeneficiaireRequest.getActivite());
        beneficiaire.setAdresse(BeneficiaireRequest.getAdresse());
       /// beneficiaire.setDelegation(BeneficiaireRequest.getDelegation());
       // beneficiaire.setGouvernorat(BeneficiaireRequest.getGouvernorat());
        beneficiaire.setRegion(BeneficiaireRequest.getRegion());
       // beneficiaire.setSecteur(BeneficiaireRequest.getSecteur());
     //   beneficiaire.setSousSecteur(BeneficiaireRequest.getSousSecteur());
        beneficiaire.setNatureActivite(BeneficiaireRequest.getNatureActivite());
     //   beneficiaire.setCodeActivite(BeneficiaireRequest.getCodeActivite());
        beneficiaire.setCodePostal(BeneficiaireRequest.getCodePostal());
        beneficiaire.setTypPersonne(BeneficiaireRequest.getTypPersonne());
       // beneficiaire.setMatriculeFiscale(BeneficiaireRequest.getMatriculeFiscale());
        return beneficiaireRepository.save(beneficiaire);
    }

    @Override
    public Beneficiaire deleteBeneficiaire(Long id) {
        Beneficiaire beneficiaire = beneficiaireRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        beneficiaireRepository.deleteById(id);
        return beneficiaire;
    }
}
