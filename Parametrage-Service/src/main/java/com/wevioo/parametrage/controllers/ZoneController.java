package com.wevioo.parametrage.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.services.QuotiteService;
import com.wevioo.parametrage.services.ZoneService;

@RestController
@RequestMapping("/zone")
public class ZoneController {
	
	@Autowired
	private ZoneService zoneService ;
	
	
	 @GetMapping("/listzones")
	 public List <Zone> listZone() {
		 return zoneService.listZone();
	 }
}
