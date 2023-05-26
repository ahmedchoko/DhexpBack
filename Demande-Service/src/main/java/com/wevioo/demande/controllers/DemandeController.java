package com.wevioo.demande.controllers;

import com.wevioo.demande.dto.DemandeDto;
import com.wevioo.demande.entities.Demande;
import com.wevioo.demande.enums.ObjetCredit;
import com.wevioo.demande.repository.DemandeRepository;
import com.wevioo.demande.servicesImpl.DemandeServiceImpl;
import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.enums.ModaliteStatut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/demande/api/v1")
public class DemandeController {
    @Autowired
    public DemandeServiceImpl demandeServiceImpl;


    @PostMapping("/createDemande")
    public ResponseEntity<Demande> createDemande(@RequestBody Demande demandeRequest) {
        if (! demandeRequest.getPartenaire().getStatut().equals( Fondstatut.ACTIF) )
        { return ResponseEntity.badRequest().build();};
        if (! (new Date()).before(demandeRequest.getPartenaire().getDateBlocage()))
        { return ResponseEntity.badRequest().build();};
        if (! demandeRequest.getFond().getStatut().equals( Fondstatut.ACTIF))
        { return ResponseEntity.badRequest().build();};
        if (! (demandeRequest.getMontantInvestissement().getMontant() <= demandeRequest.getFond().getMontantMax()) )
        { return ResponseEntity.badRequest().build();};
          if (! (demandeRequest.getMontantInvestissement().getMontant() >= demandeRequest.getFond().getMontantMin()) )
        { return ResponseEntity.badRequest().build();};
        if (! demandeRequest.getModalite().getStatut().equals(ModaliteStatut.ACTIF))
        { return ResponseEntity.badRequest().build();};
        if( ! (demandeRequest.getMontantInvestissement().getMontant() <= demandeRequest.getModalite().getMontantMax()) )
        { return ResponseEntity.badRequest().build();};
       if( ! (demandeRequest.getMontantInvestissement().getMontant() >= demandeRequest.getModalite().getMontantMin()) )
        { return ResponseEntity.badRequest().build();};
       if ( demandeRequest.getCredit().getObjetCredit().equals(ObjetCredit.CREATION)) {
           if ( demandeRequest.getMontantInvestissement().getMontant() > 15000000) {
               return ResponseEntity.badRequest().build();
           };
       };
        if ( demandeRequest.getCredit().getObjetCredit().equals(ObjetCredit.EXTENSION)) {
            if ( (demandeRequest.getCredit().getImmobilisationNettesAvantNouvelInvestissement()
                    + demandeRequest.getMontantInvestissement().getMontant() ) > 15000000) {
                return ResponseEntity.badRequest().build();
            };
        };

        try {
            Demande demande = demandeServiceImpl.createDemande(demandeRequest);
            return  ResponseEntity.ok().body(demande);
        }
        catch(NotFoundException exception) {
            return ResponseEntity.badRequest().build();

        }
    }
    @GetMapping("/demandes")
    public List<Demande> getDemandes(){

            return demandeServiceImpl.getDemandes();


    }
}
