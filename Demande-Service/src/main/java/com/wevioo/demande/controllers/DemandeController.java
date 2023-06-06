package com.wevioo.demande.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wevioo.demande.dto.DemandeDto;
import com.wevioo.demande.dto.DemandePreliminaireDTO;
import com.wevioo.demande.entities.Demande;
import com.wevioo.demande.servicesImpl.DemandeProducer;
import com.wevioo.demande.servicesImpl.DemandeServiceImpl;
import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.entities.ParametrageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@ControllerAdvice
@RequestMapping("/demande/api/v/demandes")
public class DemandeController {

    @Autowired
    public DemandeServiceImpl demandeServiceImpl;

    @Autowired
    public DemandeProducer demandeProducer;

    private Object data;


    /**
     * Ajouter une demande.
     *
     * @param demande The demande object to be added.
     * @return The response entity containing the result of the operation.
     */
    @PostMapping()
    public ResponseEntity<?> addDemandePreliminaire(@RequestBody DemandePreliminaireDTO demande) {
        ParametrageEvent parametrageevent = new ParametrageEvent();
        parametrageevent.setStatus("PENDING");
        parametrageevent.setMessage("DEMANDE status is in pending, I NEED data");
        demandeProducer.sendMessage(parametrageevent);

        try {
            Thread.sleep(2000); // 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            data = objectMapper.writeValueAsString(demandeServiceImpl.verifCritereEligibilite(demande));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * Modifier une demande.
     *
     * @param demande The demande object to be updated.
     * @return The response entity containing the updated demande object.
     */
    @PutMapping()
    public ResponseEntity<Demande> updateDemande(@RequestBody DemandeDto demande) {
        try {
            Demande updateDemande = demandeServiceImpl.updateDemande(demande);
            return ResponseEntity.ok().body(updateDemande);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    /**
     * Avoir la liste des demandes preliminaires paginées.
     *
     * @param page The page number.
     * @param size The number of items per page.
     * @return The response entity containing the paginated list of demandes preliminaires.
     */
    @GetMapping()
    public ResponseEntity<?> getDemandePreliminaire(@RequestParam(value = "page") int page,
                                                    @RequestParam(value = "size") int size) {
        Map<String, Object> jsonResponseMap = new LinkedHashMap<>();
        jsonResponseMap.put("status", 1);
        jsonResponseMap.put("pagebale", demandeServiceImpl.getDemandePreliminaire(page, size));
        return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
    }
    /**
     * Obtenir la liste des demandes paginée.
     *
     * @param page The page number.
     * @param size The number of items per page.
     * @return The response entity containing the paginated list of demandes.
     */
    @GetMapping("/getDemandes")
    public ResponseEntity<Page<Demande>> getDemandes(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Demande> demandes = demandeServiceImpl.getDemandes(page, size);
            return ResponseEntity.ok().body(demandes);
        } catch (NotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
