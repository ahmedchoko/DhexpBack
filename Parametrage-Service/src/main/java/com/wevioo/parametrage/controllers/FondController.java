package com.wevioo.parametrage.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;
import com.wevioo.parametrage.dto.FondDTO;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Secteur;
import com.wevioo.parametrage.services.FondService;


@RestController
@RequestMapping("/api/v1")
public class FondController {



	@Autowired
	private FondService fondService ;

	@Autowired
	private ModelMapper modelMapper;
	@GetMapping("/fonds")
	public ResponseEntity < ? > getFonds(
			@RequestParam(value = "MontantMaxsearchTerm",required=false) String MontantMaxsearchTerm,
			@RequestParam(value="DateDemarragesearchTerm", defaultValue = "Sat Mar 18 2023",required=false) String DateDemarragesearchTerm,
			@RequestParam(value="DateCloturesearchTerm", defaultValue = "Sat Mar 18 2023",required=false) String DateCloturesearchTerm,
			@RequestParam(value = "MontantMinsearchTerm",required=false) String MontantMinsearchTerm,
			@RequestParam(value = "StatutsearchTerm",required=false) String StatutsearchTerm,
			@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size) throws ParseException {
		Map < String, Object > jsonResponseMap = new LinkedHashMap < String, Object > ();
		Page < Fond > listofFond = fondService.getAllFond(DateDemarragesearchTerm,DateCloturesearchTerm,StatutsearchTerm,MontantMaxsearchTerm,MontantMinsearchTerm,page,size);
		List < FondDTO > listofFondDto = new ArrayList < FondDTO > ();
		if (!listofFond.isEmpty()) {
			for (Fond fond: listofFond ) {
				listofFondDto.add(modelMapper.map(fond, FondDTO.class));
			}
			jsonResponseMap.put("status", 1);
			jsonResponseMap.put("data", listofFondDto);
			jsonResponseMap.put("pagebale", listofFond);
			return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
		} else {
			jsonResponseMap.put("status", 1);
			Page < Fond > listofFond1 = fondService.getAllFond(page,size);
			for (Fond fond: listofFond1 ) {
				listofFondDto.add(modelMapper.map(fond, FondDTO.class));
			}
			List < FondDTO > listofFondDto1 = new ArrayList < FondDTO > ();
			jsonResponseMap.put("data", listofFondDto1);
			jsonResponseMap.put("pagebale", listofFond1);
			return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
		}


	}
	@PostMapping("/addFond")
	public void AddFond(@RequestBody Fond fond) {
		fondService.addFond(fond);
	}
	@GetMapping("/getFond")
	public Fond getFondById(@RequestParam() Long id) {
		return fondService.getFondById(id) ;
	}
	@GetMapping("/getFondtest")
	public List<Fond> test() {
		return fondService.listFond();
	}
	@DeleteMapping("/deleteFond")
	public void DeleteFond(@RequestParam() Long id) {
		fondService.deleteFond(id);
	}
	@PostMapping("/modifyFond")
	public void ModifyFond(@RequestBody Fond fond) {
		fondService.modifyFond(fond);
	}
	@GetMapping("/secteurs")
	public List <Secteur> listSecteur() {
		return fondService.getAllSecteur();
	}
	@GetMapping("/listFonds")
	public List <Fond> listFond() {
		return fondService.listFond();
	}
	@GetMapping("/getFonds")
	public List<Fond> getFonds() { return fondService.getNonArchivedFonds();}
	
	@GetMapping("/fondTresorieBySecteur")
	public List FondTresorieBySecteur() {
		 return fondService.FondTresorieBySecteur();
	}
	@GetMapping("/fondCountByStatus")
	public List FondCountByStatus() {
		 return fondService.FondCountByStatus();
	}
	@GetMapping("/fondTotal")
	public float FondTotal() {
		 return fondService.FondTotal();
	}
}
