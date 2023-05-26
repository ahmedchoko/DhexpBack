package com.wevioo.demande.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wevioo.demande.dto.DemandePreliminaireDTO;
import com.wevioo.demande.entities.Demande;
import com.wevioo.demande.servicesImpl.DemandeProducer;
import com.wevioo.demande.servicesImpl.DemandeServiceImpl;
import com.wevioo.parametrage.dto.FondDTO;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.ParametrageEvent;

@RestController
@RequestMapping("demande/api/v1/demande")
public class DemandeController {

    @Autowired
	private DemandeProducer parametrageProducer;
	
    @Autowired
   	private DemandeServiceImpl demandeServiceImpl;

	private Object data;
    
    
	@PostMapping("/addDemandePreliminaire")
	public ResponseEntity AddDemandePreliminaire(@RequestBody DemandePreliminaireDTO demande) {
		/*
		ParametrageEvent parametrageevent = new ParametrageEvent();
	 
		parametrageevent.setStatus("PENDING");
		parametrageevent.setMessage("DEMANDE status is in pending , I NEED data");
		parametrageProducer.sendMessage(parametrageevent);
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
		    }*/
		    return new ResponseEntity < > (data, HttpStatus.OK) ;
}
	
	@GetMapping("/getDemandePreliminaire")
	public ResponseEntity < ? > getDemandePreliminaire(@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size) {
		Map < String, Object > jsonResponseMap = new LinkedHashMap < String, Object > ();
	     jsonResponseMap.put("status", 1);
	     jsonResponseMap.put("pagebale", demandeServiceImpl.getDemandePreliminaire(page,size));
	      return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
		
	}
	
	
	
}
