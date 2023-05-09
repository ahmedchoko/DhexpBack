package com.wevioo.parametrage.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.parametrage.dto.FondDTO;
import com.wevioo.parametrage.dto.ZoneDTO;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.services.QuotiteService;
import com.wevioo.parametrage.services.ZoneService;

@RestController
@RequestMapping("/zone")
public class ZoneController {
	
	@Autowired
	private ZoneService zoneService ;
	@Autowired
	private ModelMapper modelMapper;
	
	 @GetMapping("/listzones")
	 public List <ZoneDTO> listZone() {
			List < ZoneDTO > listofZoneDto = new ArrayList < ZoneDTO > ();
		 for (Zone zone: zoneService.listZone() ) {
			 listofZoneDto.add(modelMapper.map(zone, ZoneDTO.class));
			}
		 return listofZoneDto;
	 }
}
