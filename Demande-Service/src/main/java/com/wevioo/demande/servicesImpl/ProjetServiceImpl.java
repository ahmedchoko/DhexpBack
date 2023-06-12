package com.wevioo.demande.servicesImpl;

import com.wevioo.demande.entities.Projet;
import com.wevioo.demande.repository.ProjetRepository;
import com.wevioo.demande.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service

public class ProjetServiceImpl implements ProjetService{
    @Autowired
    ProjetRepository projetRepository;
    @Override
    public Projet createProjet(Projet ProjetRequest) {
        Projet projet = Projet.builder()
               /// .approvisionnement(ProjetRequest.getApprovisionnement())
                .codePostal(ProjetRequest.getCodePostal())
                .delegation(ProjetRequest.getDelegation())
             ///   .envConcurrentiel(ProjetRequest.getEnvConcurrentiel())
                .facteurRisque(ProjetRequest.getFacteurRisque())
              ///  .garanties(ProjetRequest.getGaranties())
                .typeProjet(ProjetRequest.getTypeProjet())
              ///  .norme(ProjetRequest.getNorme())
             //   .zone(ProjetRequest.getZone())
                .site(ProjetRequest.getSite())
             ///   .moyenProduction(ProjetRequest.getMoyenProduction())
             ///   .prodOuServices(ProjetRequest.getProdOuServices())
              ////  .potentielMarche(ProjetRequest.getPotentielMarche())
             ////   .strategieComm(ProjetRequest.getStrategieComm())
                .build();
        projetRepository.save(projet);
        return projet;
    }

    @Override
    public Projet getProjetById(Long id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));

        return projet;
    }

    @Override
    public Projet updateProjet(Long id, Projet ProjetRequest) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
     ///   projet.setApprovisionnement(ProjetRequest.getApprovisionnement());
        projet.setCodePostal(ProjetRequest.getCodePostal());
        projet.setDelegation(ProjetRequest.getDelegation());
    ///    projet.setEnvConcurrentiel(ProjetRequest.getEnvConcurrentiel());
        projet.setFacteurRisque(ProjetRequest.getFacteurRisque());
      //  projet.setGaranties(ProjetRequest.getGaranties());
        projet.setTypeProjet(ProjetRequest.getTypeProjet());
     ///   projet.setNorme(ProjetRequest.getNorme());
      ///  projet.setZone(ProjetRequest.getZone());
        projet.setSite(ProjetRequest.getSite());
    ///    projet.setMoyenProduction(ProjetRequest.getMoyenProduction());
     ////   projet.setProdOuServices(ProjetRequest.getProdOuServices());
      ////  projet.setPotentielMarche(ProjetRequest.getPotentielMarche());
      ///  projet.setStrategieComm(ProjetRequest.getStrategieComm());
        return projetRepository.save(projet);
    }

    @Override
    public Projet deleteProjet(Long id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource with id "+id+" not found"));
        projetRepository.deleteById(id);
        return projet;
    }
}
