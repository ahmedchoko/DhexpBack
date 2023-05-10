package com.wevioo.parametrage.controllers;

import java.util.List;

import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.dto.StopLossDto;
import com.wevioo.parametrage.dto.ZoneDto;
import com.wevioo.parametrage.entities.StopLoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.services.QuotiteService;
import com.wevioo.parametrage.services.ZoneService;

@RestController
@RequestMapping("/api/v1/zone")
public class ZoneController {
	
	@Autowired
	private ZoneService zoneService ;
	
	
	 @GetMapping()
	 public ResponseEntity <Page<Zone>> getAllZones(@RequestParam(defaultValue = "0") int page,
												  @RequestParam(defaultValue = "20") int size) {
		try { Page<Zone> zones = zoneService.getAllZones(page, size);
			return ResponseEntity.ok().body(zones) ;   }
		catch(NotFoundException exception) { return ResponseEntity.notFound().build();  }
	 }

	@PostMapping
	public ResponseEntity<Zone> createZone(@RequestBody ZoneDto zoneRequest) {
		try {
			Zone zone = zoneService.createZone(zoneRequest);
			return ResponseEntity.ok().body(zone) ;
		}
		catch(NotFoundException exception) {
			return ResponseEntity.badRequest().build();

		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Zone> getZoneById(@PathVariable(name= "id") Long id){
		try {
			Zone zone = zoneService.getZoneById(id);
			return ResponseEntity.ok().body(zone) ;
		}
		catch(NotFoundException exception){
			return ResponseEntity.notFound().build();
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<Zone> updateZone(@PathVariable(name= "id") Long id, @RequestBody ZoneDto zoneRequest) {
		try {
			Zone zone = zoneService.updateZone(id, zoneRequest);
			return ResponseEntity.ok().body(zone) ;
		}
		catch(NotFoundException exception){
			return ResponseEntity.notFound().build();
		}

	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Zone> deleteZone(@PathVariable(name= "id") Long id) {
		try {
			Zone zone = zoneService.deleteZone(id);
			return ResponseEntity.ok().body(zone);
		} catch (NotFoundException exception) {
			return ResponseEntity.notFound().build();
		}
	}
}