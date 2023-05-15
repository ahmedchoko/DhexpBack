package com.wevioo.demande.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("demande/api/v1/credit")
public class CreditController {
	
	
	@GetMapping("/test")
	public ResponseEntity test() {
		return new ResponseEntity < > ("Gateway test", HttpStatus.OK);
	}
}
