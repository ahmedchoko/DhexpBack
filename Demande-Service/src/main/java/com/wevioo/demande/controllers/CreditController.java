package com.wevioo.demande.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.demande.entities.Demande;
import com.wevioo.demande.repository.DemandeRepository;


@RestController
@RequestMapping("demande/api/v1/credit")
public class CreditController {
	
	@Autowired 
	public DemandeRepository demandeRepository;
	
	@GetMapping("/test")
	public ResponseEntity test() {
		//Demande demande = demandeRepository.findById((long)1).get();
		return new ResponseEntity < > ("demande.getFond().getIdFond()", HttpStatus.OK);
	}
}
