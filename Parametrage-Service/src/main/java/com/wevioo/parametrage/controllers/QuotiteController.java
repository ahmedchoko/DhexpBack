package com.wevioo.parametrage.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.parametrage.dto.FondDTO;
import com.wevioo.parametrage.dto.QuotiteDTO;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.entities.Quotite;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.services.QuotiteService;


@RestController
@RequestMapping("parametrage/api/v1/quotite")
public class QuotiteController {
	 
	@Autowired
	private QuotiteService quotiteService ;
	
	@Autowired
	private ModelMapper modelMapper;
	 @GetMapping("/quotites")
	  public ResponseEntity < ? > getQuotites(
			                               @RequestParam(value = "fondsearchTerm",required=false) String fondsearchTerm,
			                               @RequestParam(value = "zonesearcnhTerm",required=false) String zonesearchTerm,
			                               @RequestParam(value="zonalsearchTerm", required=false) String zonalsearchTerm,
			                               @RequestParam(value="ritic",required=false) String ritic,
			                               @RequestParam(value = "nouveauProm",required=false) String nouveauProm,
			                               @RequestParam(value = "creditLeasing",required=false) String creditLeasing,
			                               @RequestParam(value = "page") int page,
                                           @RequestParam(value = "size") int size) throws ParseException {
	    Map < String, Object > jsonResponseMap = new LinkedHashMap < String, Object > ();
	    Page < Quotite > listofQuotite = quotiteService.getAllQuotite(page, size, fondsearchTerm, zonesearchTerm,zonalsearchTerm, ritic, nouveauProm, creditLeasing);
	    List < QuotiteDTO > listofQuotiteDto = new ArrayList < QuotiteDTO > ();
	    if (!listofQuotite.isEmpty()) {
	      for (Quotite quotite: listofQuotite ) {
	    	  listofQuotiteDto.add(modelMapper.map(quotite, QuotiteDTO.class));
	      }
	      jsonResponseMap.put("status", 1);
	     jsonResponseMap.put("data", listofQuotiteDto);
	     jsonResponseMap.put("pagebale", listofQuotite);
	      return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
	    } else {
	     jsonResponseMap.put("status", 1);
	     Page < Quotite > listofQuotite1 = quotiteService.getAllQuotite(page, size);
	     for (Quotite quotite: listofQuotite1 ) {
	    	 listofQuotiteDto.add(modelMapper.map(quotite, QuotiteDTO.class));
	      }
	     List < QuotiteDTO > listofQuotiteDto1 = new ArrayList < QuotiteDTO > ();
	      jsonResponseMap.put("data", listofQuotiteDto1);
		     jsonResponseMap.put("pagebale", listofQuotiteDto1);
	      return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
	    }
	 }
	 @PostMapping("/addQuotite")
		public void AddQuotite(@RequestBody Quotite quotite) {
		 quotiteService.addQuotite(quotite)	;	
		 }
		@GetMapping("/getQuotite")
		public Quotite getQuotiteById(@RequestParam() Long id) {
			return quotiteService.getQuotiteById(id);
		}
		@PostMapping("/modifyQuotite")
		 public Long ModifyQuotite(@RequestBody Quotite quotite) {
			 return quotiteService.modifyQuotite(quotite);
		 }
}

