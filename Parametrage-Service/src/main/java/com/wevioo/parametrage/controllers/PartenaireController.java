package com.wevioo.parametrage.controllers;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.parametrage.dto.FondDTO;
import com.wevioo.parametrage.dto.PartenaireDTO;
import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.services.PartenaireService;


@RestController
@RequestMapping("/partenaire")
public class PartenaireController {

	
	@Autowired
	private PartenaireService partenaireService ;
	@Autowired
	private FondService fondService ;
	
	@Autowired
	private ModelMapper modelMapper;
	 @GetMapping("/partenaires")
	  public ResponseEntity < ? > getPartenaires(  @RequestParam(value = "searchFond",required=false) String searchFond,
			                                       @RequestParam(value = "MontantMaxsearchTerm",required=false) String MontantMaxsearchTerm,
                                                   @RequestParam(value = "MontantMinsearchTerm",required=false) String MontantMinsearchTerm,
                                                   @RequestParam(value = "StatutsearchTerm",required=false) String StatutsearchTerm,
			                               @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "3") int size) throws ParseException {
	    Map < String, Object > jsonResponseMap = new LinkedHashMap < String, Object > ();
	    System.out.println("MontantMinsearchTerm"+MontantMinsearchTerm);
	    Page < Partenaire > listofParteanaire = partenaireService.getAllPartenaire(searchFond ,MontantMinsearchTerm,MontantMaxsearchTerm,StatutsearchTerm,page,size);
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

	        jsonResponseMap.put("status", 1);
	        Page < Partenaire > listofPartenaire1 = partenaireService.getPartenaireList(page, size);
		     for (Partenaire partenaire: listofPartenaire1 ) {
		    	 listofPartenaireDTO.add(modelMapper.map(partenaire, PartenaireDTO.class));
		      }
		     List < PartenaireDTO > listofPartenaireDto1 = new ArrayList < PartenaireDTO > ();
		      jsonResponseMap.put("data", listofPartenaireDto1);
			     jsonResponseMap.put("pagebale", listofPartenaire1);
	      return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
		
		      
	    }
	  }
	 @GetMapping("/getPartenaire")
     public Partenaire getPartenaireById(@RequestParam() Long id) {
		 return partenaireService.getPartenaireById(id) ;
		 }
	 @GetMapping("/partenaire/modalites")
	public List <Modalite> getAllModalite( @RequestParam(defaultValue = "1") Long IdPartenaire) {
		 return this.partenaireService.getAllModaliteOfpartenaire(IdPartenaire);
	 }
	 @PostMapping("/addpartenaire")
	 public void AddPartenaire(@RequestBody Partenaire partenaire) {
		 partenaireService.addPartenaire(partenaire);
	 }
	 @PutMapping("/modifyPartenaire")
	 public void ModifyPartenaire(@RequestBody Partenaire partenaire) {
		 partenaireService.modifyPartenaire(partenaire);
	 }
	 @PostMapping("/addpartenairewithcvt")
	 public void AddPartenairewithcvt(@RequestBody List <Convention>  conventions) {
		 partenaireService.addPartenairewithcvt(conventions);
	 }
	 @PutMapping("/modifypartenairewithcvt")
	 public void ModifyPartenairewithcvt(@RequestBody List <Convention>  conventions) {
		 partenaireService.modifyPartenairewithcvt(conventions);
	 }
	 @GetMapping("/partenaire/fonds")
		public ResponseEntity < ? >  getAllFondofpartenaire(@RequestParam(defaultValue = "") String firstNameFilter,
                @RequestParam(defaultValue = "1") int page,
                @RequestParam(defaultValue = "3") int size) {
		  Map < String, List<Fond>> jsonResponseMap = new LinkedHashMap < String, List<Fond >> ();
		  Map < String, Object > PartenairejsonResponseMap = new LinkedHashMap < String, Object > ();
          Map  < Partenaire,Fond > test = new HashMap <  Partenaire,Fond> ();
           ///Fond fond = new Fond(); 
		  List  res = new ArrayList(); 
		   PartenaireDTO partenaireDTO = new PartenaireDTO();
		 
		/* for(Partenaire partenaire : this.partenaireService.getAllPartenaire(firstNameFilter, page, size)) {
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
		 
		///System.out.println(fonds);
		 //FondsjsonResponseMap.put("res", res); 
		// jsonResponseMap.put("res", FondsjsonResponseMap);
		// jsonResponseMap.put("pagebale", this.partenaireService.getAllPartenaire(firstNameFilter, page, size));
			 return new ResponseEntity < > ( this.fondService.getAllFondofPartenaire( (long) 1), HttpStatus.OK);
		 }
}
