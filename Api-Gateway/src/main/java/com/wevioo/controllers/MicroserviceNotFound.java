package com.wevioo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class MicroserviceNotFound {

	
	
	@RestController
	public class GatewayController {
	  
	  @GetMapping("/parametrage-service-not-found")
	  public ResponseEntity<Object> handleParametrageServiceNotFound() {
	    String errorMessage = "Parametrage service not found";
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	  }
	  @GetMapping("/demande-service-not-found")
	  public ResponseEntity<Object> handleDemandeServiceNotFound() {
	    String errorMessage = "Demande service not found";
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	  }
	}
}
