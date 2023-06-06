package com.wevioo.parametrage.controllers;

import java.util.ArrayList;
import java.util.List;

import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.dto.StopLossDto;
import com.wevioo.parametrage.dto.ZoneDTO;
import com.wevioo.parametrage.entities.StopLoss;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.services.QuotiteService;
import com.wevioo.parametrage.services.ZoneService;

@RestController
@RequestMapping("/parametrage/api/v1/zones")
public class ZoneController {

	@Autowired
	private ZoneService zoneService;
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Get all zones (paginated).
	 *
	 * @param page the page number
	 * @param size the number of items per page
	 * @return the paginated list of zones
	 */
	@GetMapping()
	public ResponseEntity<Page<Zone>> getAllZones(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {
		try {
			Page<Zone> zones = zoneService.getAllZones(page, size);
			return ResponseEntity.ok().body(zones);
		} catch (NotFoundException exception) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Create a new zone.
	 *
	 * @param zoneRequest the zone data
	 * @return the created zone
	 */
	@PostMapping
	public ResponseEntity<Zone> createZone(@RequestBody ZoneDTO zoneRequest) {
		try {
			Zone zone = zoneService.createZone(zoneRequest);
			return ResponseEntity.ok().body(zone);
		} catch (NotFoundException exception) {
			return ResponseEntity.badRequest().build();
		}
	}

	/**
	 * Get a zone by its ID.
	 *
	 * @param id the zone ID
	 * @return the zone with the given ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Zone> getZoneById(@PathVariable(name = "id") Long id) {
		try {
			Zone zone = zoneService.getZoneById(id);
			return ResponseEntity.ok().body(zone);
		} catch (NotFoundException exception) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Update a zone by its ID.
	 *
	 * @param id           the zone ID
	 * @param zoneRequest the updated zone data
	 * @return the updated zone
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Zone> updateZone(@PathVariable(name = "id") Long id, @RequestBody ZoneDTO zoneRequest) {
		try {
			Zone zone = zoneService.updateZone(id, zoneRequest);
			return ResponseEntity.ok().body(zone);
		} catch (NotFoundException exception) {
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * Delete a zone by its ID.
	 *
	 * @param id the zone ID
	 * @return the deleted zone
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Zone> deleteZone(@PathVariable(name = "id") Long id) {
		try {
			Zone zone = zoneService.deleteZone(id);
			return ResponseEntity.ok().body(zone);
		} catch (NotFoundException exception) {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Get the list of all zones (non-paginated) to be displayed in a selector on the
	 * frontend.
	 *
	 * @return the list of zones
	 */
	@GetMapping("/listZone")
	public List<ZoneDTO> listZone() {
		List<ZoneDTO> listofZoneDto = new ArrayList<ZoneDTO>();
		for (Zone zone : zoneService.listZone()) {
			listofZoneDto.add(modelMapper.map(zone, ZoneDTO.class));
		}
		return listofZoneDto;
	}

	/**
	 * Get the list of zones that are not assigned to any quotite.
	 *
	 * @return the list of zones without assignment
	 */
	@GetMapping("/listZoneWithoutAffectation")
	public ResponseEntity<List<Zone>> listZoneWithoutAffectation() {
		try {
			List<Zone> zones = zoneService.listZoneWithoutAffectation();
			return ResponseEntity.ok().body(zones);
		} catch (NotFoundException exception) {
			return ResponseEntity.notFound().build();
		}
	}
}
