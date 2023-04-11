package com.wevioo.parametrage.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.wevioo.parametrage.services.FondService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class FondController {

	
	@Autowired
	private FondService fondService ;
	
	@Autowired
	private ModelMapper modelMapper;
	 @GetMapping("/getAllFonds")
	  public ResponseEntity < ? > getFonds(@RequestParam(defaultValue = "") String firstNameFilter,
			                               @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "3") int size) {
	    Map < String, Object > jsonResponseMap = new LinkedHashMap < String, Object > ();
	    Page < Fond > listofFond = fondService.getAllFond(firstNameFilter,page,size);
	   // System.out.println(listofFond);
	    List < FondDTO > listofFondDto = new ArrayList < FondDTO > ();
	    if (!listofFond.isEmpty()) {
	      for (Fond fond: listofFond ) {
	    	  listofFondDto.add(modelMapper.map(fond, FondDTO.class));
	      }
	      jsonResponseMap.put("status", 1);
	     jsonResponseMap.put("data", listofFondDto);
	     jsonResponseMap.put("pageable", listofFond);
	      return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
	    } else {
	      jsonResponseMap.clear();
	     jsonResponseMap.put("status", 0);
	      jsonResponseMap.put("message", "Data is not found");
	      return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
	    }
	  }
	 @PostMapping("/addFond")
	 public Fond AddFond(@RequestBody Fond fond) {
		 return fondService.addFond(fond);
	 }
	 @DeleteMapping("/deleteFond")
	 public void DeleteFond(@RequestParam() Long id) {
		 fondService.deleteFond(id);
	 }
	 @PutMapping("/modifyFond")
	 public void ModifyFond(@RequestBody Fond fond) {
		fondService.modifyFond(fond);
	 }
	 @GetMapping("/getFond")
	public Fond getFondById(@RequestParam Long id) { return fondService.getFondById(id);}
	@GetMapping("/getFonds")
	public List<Fond> getFonds() { return fondService.getNonArchivedFonds();}


}
