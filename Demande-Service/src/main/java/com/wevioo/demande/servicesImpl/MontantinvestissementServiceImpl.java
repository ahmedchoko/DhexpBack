package com.wevioo.demande.servicesImpl;

import com.wevioo.demande.entities.MontantInvestissement;
import com.wevioo.demande.repository.MontantInvestissementRepository;
import com.wevioo.demande.services.MontantInvestissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service

public class MontantinvestissementServiceImpl implements MontantInvestissementService {
    @Autowired
    MontantInvestissementRepository montantInvestissementRepository;
    @Override
    public MontantInvestissement createMontantInvestissement(MontantInvestissement MontantInvestissementRequest) {
        MontantInvestissement montantInvestissement = MontantInvestissement.builder()
                .autoFinancement(MontantInvestissementRequest.getAutoFinancement())
                .capAugcap(MontantInvestissementRequest.getCapAugcap())
                .ccAssocies(MontantInvestissementRequest.getCcAssocies())
                .creditCT(MontantInvestissementRequest.getCreditCT())
                .creditExterieur(MontantInvestissementRequest.getCreditExterieur())
                .creditLeasing(MontantInvestissementRequest.getCreditLeasing())
                .creditTerme(MontantInvestissementRequest.getCreditTerme())
                .equipProd(MontantInvestissementRequest.getEquipProd())
                .fondsRoulement(MontantInvestissementRequest.getFondsRoulement())
                .foprodiRiti(MontantInvestissementRequest.getFoprodiRiti())
                .fraisAppDivers(MontantInvestissementRequest.getFraisAppDivers())
                .fraisEtablissement(MontantInvestissementRequest.getFraisEtablissement())
                .genieCivilAmenag(MontantInvestissementRequest.getGenieCivilAmenag())
                .montantContribution(MontantInvestissementRequest.getMontantContribution())
                .sicar(MontantInvestissementRequest.getSicar())
                .ppqf(MontantInvestissementRequest.getPpqf())
                .matTransport(MontantInvestissementRequest.getMatTransport())
                .terrain(MontantInvestissementRequest.getTerrain())
                .resDeuxDerBilans(MontantInvestissementRequest.getResDeuxDerBilans())
                .matBureau(MontantInvestissementRequest.getMatBureau())
                .build();
        return montantInvestissementRepository.save(montantInvestissement);
    }

    @Override
    public MontantInvestissement getMontantInvestissementById(Long id) {
        MontantInvestissement montantInvestissement = montantInvestissementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));

        return montantInvestissement;
    }

    @Override
    public MontantInvestissement updateMontantInvestissement(Long id, MontantInvestissement MontantInvestissementRequest) {
        MontantInvestissement montantInvestissement = montantInvestissementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        montantInvestissement.setAutoFinancement(MontantInvestissementRequest.getAutoFinancement());
        montantInvestissement.setCapAugcap(MontantInvestissementRequest.getCapAugcap());
        montantInvestissement.setCcAssocies(MontantInvestissementRequest.getCcAssocies());
        montantInvestissement.setCreditCT(MontantInvestissementRequest.getCreditCT());
        montantInvestissement.setCreditLeasing(MontantInvestissementRequest.getCreditLeasing());
        montantInvestissement.setCreditTerme(MontantInvestissementRequest.getCreditTerme());
        montantInvestissement.setMontantContribution(MontantInvestissementRequest.getMontantContribution());
        montantInvestissement.setCreditExterieur(MontantInvestissementRequest.getCreditExterieur());
        montantInvestissement.setEquipProd(MontantInvestissementRequest.getEquipProd());
        montantInvestissement.setFondsRoulement(MontantInvestissementRequest.getFondsRoulement());
        montantInvestissement.setFoprodiRiti(MontantInvestissementRequest.getFoprodiRiti());
        montantInvestissement.setFraisAppDivers(MontantInvestissementRequest.getFraisAppDivers());
        montantInvestissement.setFraisEtablissement(MontantInvestissementRequest.getFraisEtablissement());
        montantInvestissement.setGenieCivilAmenag(MontantInvestissementRequest.getGenieCivilAmenag());
        montantInvestissement.setMatBureau(MontantInvestissementRequest.getMatBureau());
        montantInvestissement.setMatTransport(MontantInvestissementRequest.getMatTransport());
        montantInvestissement.setTerrain(MontantInvestissementRequest.getTerrain());
        montantInvestissement.setSicar(MontantInvestissementRequest.getSicar());
        montantInvestissement.setPpqf(MontantInvestissementRequest.getPpqf());
        montantInvestissement.setResDeuxDerBilans(MontantInvestissementRequest.getResDeuxDerBilans());
        return montantInvestissementRepository.save(montantInvestissement);
    }

    @Override
    public MontantInvestissement deleteMontantInvestissement(Long id) {
        MontantInvestissement montantInvestissement = montantInvestissementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        montantInvestissementRepository.deleteById(id);
        return montantInvestissement;
    }
}
