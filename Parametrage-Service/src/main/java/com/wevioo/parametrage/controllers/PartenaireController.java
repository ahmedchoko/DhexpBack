package com.wevioo.parametrage.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.parametrage.dto.PartenaireDTO;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.services.PartenaireService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class PartenaireController {

	
	@Autowired
	private PartenaireService partenaireService ;
	@Autowired
	private FondService fondService ;
	
	@Autowired
	private ModelMapper modelMapper;
	 @GetMapping("/partenaires")
	  public ResponseEntity < ? > getPartenaires(@RequestParam(defaultValue = "") String firstNameFilter,
			                               @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "3") int size) {
	    Map < String, Object > jsonResponseMap = new LinkedHashMap < String, Object > ();
	    Page < Partenaire > listofParteanaire = partenaireService.getAllPartenaire(firstNameFilter,page,size);
	    System.out.println(listofParteanaire);
	    List < PartenaireDTO > listofPartenaireDTO = new ArrayList < PartenaireDTO > ();
	    if (!listofParteanaire.isEmpty()) {
	      for (Partenaire partenaire: listofParteanaire ) {
	    	  listofPartenaireDTO.add(modelMapper.map(partenaire, PartenaireDTO.class));
	      }
	      jsonResponseMap.put("status", 1);
	     jsonResponseMap.put("data", listofPartenaireDTO);
	     jsonResponseMap.put("pagebale", listofParteanaire);
	      return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
	    } else {
	      jsonResponseMap.clear();
	     jsonResponseMap.put("status", 0);
	      jsonResponseMap.put("message", "Data is not found");
	      return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
	    }
	  }
	 @GetMapping("/partenaire/modalites")
	public List <Modalite> getAllModalite( @RequestParam(defaultValue = "1") Long IdPartenaire) {
		 return this.partenaireService.getAllModaliteOfpartenaire(IdPartenaire);
	 }
	 @GetMapping("/partenaire/fonds")
		public ResponseEntity < ? >  getAllFondofpartenaire(@RequestParam(defaultValue = "") String firstNameFilter,
                @RequestParam(defaultValue = "1") int page,
                @RequestParam(defaultValue = "3") int size) {
		  Map < String, List<Fond>> jsonResponseMap = new LinkedHashMap < String, List<Fond >> ();
		  Map < String, Object > PartenairejsonResponseMap = new LinkedHashMap < String, Object > ();
          Map  < Partenaire,Fond > test = new HashMap <  Partenaire,Fond> ();
           ///Fond fond = new Fond(); 
		  List <Fond> fonds = new ArrayList(); 
		  List  res = new ArrayList(); 
		   PartenaireDTO partenaireDTO = new PartenaireDTO();
		 for(Partenaire partenaire : this.partenaireService.getAllPartenaire(firstNameFilter, page, size)) {
			 for(Fond fond :this.fondService.getAllFondofPartenaire(partenaire.getIdPartenaire()) ) {
				/// System.out.println("fond "+fond.toString()+"pour partenaire "+partenaire.getIdPartenaire());
				/// System.out.println(fond.toString());
				 fonds.add(fond);
			 }
			// test.put(partenaire,fonds.get(0));
			// modelMapper.map(partenaire, PartenaireDTO.class);
			// System.out.println(partenaire.getIdPartenaire());
			/* System.out.println("fonds size bef " +fonds.size());
			 partenaireDTO.setIdPartenaire(partenaire.getIdPartenaire());
			 partenaireDTO.setNomArabePartenaire(partenaire.getNomArabePartenaire());
			 partenaireDTO.setTypePartenaire(partenaire.getTypePartenaire());
			 partenaireDTO.setAbrevPartenaire(partenaire.getAbrevPartenaire());
			 partenaireDTO.setAdresse(partenaire.getAdresse());
			 partenaireDTO.setCodeBic(partenaire.getCodeBic());
			 partenaireDTO.setDateActivation(partenaire.getDateActivation());
			 partenaireDTO.setDateBlocage(partenaire.getDateBlocage());
			 partenaireDTO.setNumFax(partenaire.getNumFax());
			 partenaireDTO.setNumTelephone(partenaire.getNumTelephone());
			 partenaireDTO.setSite(partenaire.getSite());
			 partenaireDTO.setNomCompletPartenaire(partenaire.getNomArabePartenaire());
			 partenaireDTO.setStatut(partenaire.getStatut());
			/// System.out.println("fonds size " +partenaireDTO.getFonds().size());
			 res.add(partenaireDTO);
			 System.out.println("ress size" +res.size());
			// System.out.println("fonds size after cler " +partenaireDTO.getFonds().size());
			 partenaireDTO=new PartenaireDTO() ;
			 */
			/// fond.setModalites(null)
			// res.add(fonds);
			 ///res.add(partenaire, fonds);
			
			/// jsonResponseMap.put("fnds of "+partenaire.getIdPartenaire(), fonds);
			/// fonds = new ArrayList();
		 } 
		///System.out.println(fonds);
		 //FondsjsonResponseMap.put("res", res); 
		// jsonResponseMap.put("res", FondsjsonResponseMap);
		// jsonResponseMap.put("pagebale", this.partenaireService.getAllPartenaire(firstNameFilter, page, size));
			 return new ResponseEntity < > (fonds, HttpStatus.OK);
		 }
}
