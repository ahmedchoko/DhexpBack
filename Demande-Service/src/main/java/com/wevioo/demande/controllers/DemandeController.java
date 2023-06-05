package com.wevioo.demande.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wevioo.demande.dto.DemandeDto;
import com.wevioo.demande.dto.DemandePreliminaireDTO;
import com.wevioo.demande.entities.Demande;
import com.wevioo.demande.enums.ObjetCredit;
import com.wevioo.demande.repository.DemandeRepository;
import com.wevioo.demande.servicesImpl.DemandeProducer;
import com.wevioo.demande.servicesImpl.DemandeServiceImpl;
import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.ParametrageEvent;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.enums.ModaliteStatut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@ControllerAdvice
@RequestMapping("/demande/api/v1")
public class DemandeController {
    @Autowired
    public DemandeServiceImpl demandeServiceImpl;
    @Autowired
    public DemandeProducer demandeProducer;
    	private Object data;
    @PostMapping("/createDemande")
    public ResponseEntity<Demande> createDemande(@RequestBody Demande demandeRequest) {
		return null;
      /*  if (! demandeRequest.getPartenaire().getStatut().equals( Fondstatut.ACTIF) )
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

        }*/
    
}

            @GetMapping("/getDemandePreliminaire")
          	public ResponseEntity < ? > getDemandePreliminaire(@RequestParam(value = "page") int page,
          			@RequestParam(value = "size") int size) {
          		Map < String, Object > jsonResponseMap = new LinkedHashMap < String, Object > ();
          	     jsonResponseMap.put("status", 1);
          	     jsonResponseMap.put("pagebale", demandeServiceImpl.getDemandePreliminaire(page,size));
          	      return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);

          	}
            @PostMapping("/addDemandePreliminaire")
          	public ResponseEntity AddDemandePreliminaire(@RequestBody DemandePreliminaireDTO demande) {     		
          		ParametrageEvent parametrageevent = new ParametrageEvent();
          		parametrageevent.setStatus("PENDING");
          		parametrageevent.setMessage("DEMANDE status is in pending , I NEED data");
          		demandeProducer.sendMessage(parametrageevent);
          		  try {
          		        Thread.sleep(2000); // 2 seconds
          		    } catch (InterruptedException e) {
          		        e.printStackTrace();
          		    }
          		  ObjectMapper objectMapper = new ObjectMapper();
          		    try {
          		         data = objectMapper.writeValueAsString(demandeServiceImpl.VerifCritereEligibilite(demande));
          		    } catch (JsonProcessingException e) {
          		        // Handle the exception appropriately
          		        e.printStackTrace();
          		        return null;
          		    }
          		    return new ResponseEntity < > (data, HttpStatus.OK) ;
          }
		  @PostMapping("/updateDemande")
	public ResponseEntity<Demande> updateDemande(@RequestBody DemandeDto demande) {
		try {
			Demande updateDemande = demandeServiceImpl.updateDemande(demande);
			return  ResponseEntity.ok().body(updateDemande);

		}
		catch (Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	};
	@GetMapping("/getDemandes")
	public ResponseEntity<Page<Demande>> updateDemande(@RequestParam(defaultValue = "0") int page,
													   @RequestParam(defaultValue = "10") int size){

			try { Page<Demande> demandes = demandeServiceImpl.getDemandes(page, size);
				return ResponseEntity.ok().body(demandes) ;   }
			catch(NotFoundException exception) { return ResponseEntity.notFound().build();  }

	};


}
