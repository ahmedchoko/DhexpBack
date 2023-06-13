package com.wevioo.parametrage.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.ValidationException;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.boot.devtools.filewatch.FileSystemWatcherFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wevioo.parametrage.dto.FondDTO;
import com.wevioo.parametrage.dto.PartenaireDTO;
import com.wevioo.parametrage.dto.UtilisateurDTO;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.entities.Utilisateur;
import com.wevioo.parametrage.repository.UtilisateurRepository;
import com.wevioo.parametrage.services.UtilisateurService;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/parametrage/api/v1/utilisateurs")
public class UtilisateurController {
	

	// Create the resource pattern resolver bean
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UtilisateurService utilisateurservice ; 
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	   /**
     * Get the user .
     *
     * @param id of the user
     * @return the user based on its id
     */
	@GetMapping("/{id}")
	public ResponseEntity<?> getUtilisateurById(@PathVariable(name = "id") String id) {
		 try {
	            Utilisateur utilisateur = utilisateurservice.getUtilisateur(id);
	            if (utilisateur != null) {
	                return ResponseEntity.ok(utilisateur);
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (Exception e) {
	            // Handle the exception
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving the user.");
	        }
	}
	   /**
     * add the user .
     *
     * @param utilisateur the user data
     * @return  the created user
     */
	@PostMapping()
public ResponseEntity<?> addUtilisateurWithoutImage(@RequestBody Utilisateur utilisateur) {
		
	   try {
           Utilisateur utilisateursaved = utilisateurservice.addUtilisateur(utilisateur);
           return ResponseEntity.status(HttpStatus.CREATED).body(utilisateursaved);
       } catch (ValidationException e) {
           // Handle validation errors
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       } catch (Exception e) {
           // Handle the exception
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating the user.");
       }
	}
	   /**
     * add the user .
     *
     * @param utilisateurPartMono the user data
     * @param file the image data
     * @return  the created user
     */
	@PostMapping("/image")
	public Mono<Utilisateur> addUtilisateur(@RequestPart("utilisateur") Mono<FormFieldPart> utilisateurPartMono,
	                                        @RequestPart(value = "file", required = false) FilePart file) throws IOException {
	    return utilisateurPartMono.flatMap(utilisateurPart -> {
	        // Get the utilisateur JSON string
	        String utilisateurJson = utilisateurPart.value();

	        // Convert the JSON string to the Utilisateur object
	        ObjectMapper objectMapper = new ObjectMapper();
	        Utilisateur utilisateur = null;
	        try {
	            utilisateur = objectMapper.readValue(utilisateurJson, Utilisateur.class);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }

	        // Save the utilisateur object first
	        Utilisateur savedUtilisateur = null;
	        try {
	            savedUtilisateur = utilisateurservice.addUtilisateur(utilisateur);
	        } catch (ValidationException e) {
	            // Return an error response
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
	        } catch (Exception e) {
	            // Handle other exceptions
	            e.printStackTrace();
	            // Return an error response
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating the user.");
	        }

	        // Process and save the uploaded file if available
	        if (file != null) {
	            return utilisateurservice.savefile(savedUtilisateur, file);
	        } else {
	            // Return the saved utilisateur without processing the file
	            return Mono.just(savedUtilisateur);
	        }
	    });
	}

	   /**
  * set the user activated.
  *
  * @param id the user id
  */
	@PostMapping("/{id}")
	public void SetActivatedUtilisateur(@PathVariable(name = "id")  Long id) {
		
		 utilisateurservice.setActivatedUtilisateur(id) ;
	}
	 /**
     * Get all users .
     *
     * @param page the page number
     * @param size the number of items per page
     * @return the paginated list of users
     */
	@GetMapping()
	public ResponseEntity < ? > getUtilisateurs(
			@RequestParam(value = "page") int page,
			@RequestParam(value = "size") int size) throws ParseException {
		Map < String, Object > jsonResponseMap = new LinkedHashMap < String, Object > ();
		Page < Utilisateur > listofUtilisateurs = utilisateurservice.getAllUtilisateur(page,size);
		List < UtilisateurDTO > listofUtilisateurDto = new ArrayList < UtilisateurDTO > ();
		if (!listofUtilisateurs.isEmpty()) {
			for (Utilisateur utilisateur: listofUtilisateurs ) {
				listofUtilisateurDto.add(modelMapper.map(utilisateur, UtilisateurDTO.class));
			}
			jsonResponseMap.put("status", 1);
			jsonResponseMap.put("data", listofUtilisateurDto);
			jsonResponseMap.put("pagebale", listofUtilisateurs);
			return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
		} else {
			jsonResponseMap.put("status", 1);
			Page < Utilisateur > listofUtilisateurs1 = utilisateurservice.getAllUtilisateur(page,size);
			for (Utilisateur utilisateur: listofUtilisateurs1 ) {
				listofUtilisateurDto.add(modelMapper.map(utilisateur, UtilisateurDTO.class));
			}
			List < UtilisateurDTO > listofUtilisateurDto1 = new ArrayList < UtilisateurDTO > ();
			jsonResponseMap.put("data", listofUtilisateurDto1);
			jsonResponseMap.put("pagebale", listofUtilisateurs1);
			return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
		}

	}
}
