package com.wevioo.parametrage.servicesimpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import javax.validation.ValidationException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.entities.Utilisateur;
import com.wevioo.parametrage.repository.UtilisateurRepository;
import com.wevioo.parametrage.services.UtilisateurService;

import reactor.core.publisher.Mono;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
	private final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

	@Autowired
	private UtilisateurRepository utilisateurrepo ;
	
	
	@Override
	public Utilisateur addUtilisateur(Utilisateur utilisateur) throws ValidationException{
	    try {
	    	if(utilisateur.getIdUtilisateur()!=null) {
		        validatemodifyUtilisateur(utilisateur);
		    	return utilisateurrepo.save(utilisateur);

	    	}
	    	else {
		        validateAddUtilisateur(utilisateur);
		        Utilisateur util = new Utilisateur();
		        util.setUserkeycloakId(utilisateur.getUserkeycloakId());
		        util.setEmail(utilisateur.getEmail());
		        util.setUsername(utilisateur.getUsername());
		        util.setRoleUser(utilisateur.getRoleUser());
		        util.setGroupUser(utilisateur.getGroupUser());
		        util.setTypePartenaire(utilisateur.getTypePartenaire());
		        util.setTypeStructure(utilisateur.getTypeStructure());
		    	return utilisateurrepo.save(util);

	    	}
	    } catch (IllegalArgumentException ex) {
	    	throw new ValidationException(ex.getMessage());	    }
	}
   public void validatemodifyUtilisateur(Utilisateur utilisateur)throws IllegalArgumentException {
	   // Perform pattern validations
	    String emailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
	    String telephonePattern = "^(5[0-9]{7}|2[0-9]{7}|9[0-9]{7})$";
        String cinPattern = "\\d{8}";
	    // Validate non-nullable and format of attributes
	    if (utilisateur.getEmail() == null || !utilisateur.getEmail().matches(emailPattern)) {
	        throw new IllegalArgumentException("Invalid email format.");
	    }
	    if (utilisateur.getTelephone() == null || !utilisateur.getTelephone().toString().matches(telephonePattern)) {
	        throw new IllegalArgumentException("Invalid telephone number format.");
	    }
	    if (utilisateur.getCin() == null || !utilisateur.getCin().toString().matches(cinPattern)) {
	        throw new IllegalArgumentException("Invalid cin number format.");
	    }
	    // Check uniqueness of attributes (assuming these attributes should be unique)
	    if (utilisateur.getEmail() != null) {
	        Utilisateur existingUserByEmail = utilisateurrepo.findByEmailExceptId(utilisateur.getIdUtilisateur(),utilisateur.getEmail());
	        if (existingUserByEmail != null) {
	            throw new IllegalArgumentException("Email must be unique.");
	        }
	    }

	    if (utilisateur.getTelephone() != null) {
	        Utilisateur existingUserByTelephone = utilisateurrepo.findByTelephoneExceptId(utilisateur.getIdUtilisateur(),utilisateur.getTelephone());
	        if (existingUserByTelephone != null) {
	            throw new IllegalArgumentException("Telephone number must be unique.");
	        }
	    }
	    if (utilisateur.getCin() != null) {
	        Utilisateur existingUserByCin = utilisateurrepo.findByCinExceptId(utilisateur.getIdUtilisateur(),utilisateur.getCin());
	        if (existingUserByCin != null) {
	            throw new IllegalArgumentException("cin number must be unique.");
	        }
	    }
	    if (utilisateur.getUserkeycloakId() != null) {
	        Utilisateur existingUserByKeycloakId = utilisateurrepo.findByUserkeycloakIdExceptId(utilisateur.getIdUtilisateur(),utilisateur.getUserkeycloakId());
	        if (existingUserByKeycloakId != null) {
	            throw new IllegalArgumentException("userkeycloakId must be unique.");
	        }
	    }
	   
   }
	public void validateAddUtilisateur(Utilisateur utilisateur) throws IllegalArgumentException {
	    if (utilisateur.getUserkeycloakId() != null) {
	        Utilisateur existingUserByKeycloakId = utilisateurrepo.findByUserkeycloakId(utilisateur.getUserkeycloakId());
	        if (existingUserByKeycloakId != null) {
	            throw new IllegalArgumentException("userkeycloakId must be unique.");
	        }
	    }
	}

	@Override
	public Utilisateur getUtilisateur(String utilisateurId) throws NotFoundException {
	    try {
	        Utilisateur utilisateur = utilisateurrepo.findByUserkeycloakId(utilisateurId);
	        if (utilisateur != null) {
	            return utilisateur;
	        } else {
	            // Handle the case when the user does not exist
	            throw new NotFoundException("User not found with id: " + utilisateurId);
	        }
	    } catch (NotFoundException e) {
	        throw new NotFoundException("Error: " + e.getMessage());
	    }
	}



	@Override
	public Page<Utilisateur> getAllUtilisateur(int page, int size)throws NotFoundException {
	    try {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Utilisateur> utilisateurs =  utilisateurrepo.findAll(pageable) ;
	        if(utilisateurs.isEmpty()) {
	            throw new NotFoundException("there is no data ");

	        }else {
		        return utilisateurrepo.findAll(pageable);
	        }
	        } catch (NotFoundException e) {
	    	throw new NotFoundException("error " + e.getMessage());
	    }
	}


	@Override
	public void setActivatedUtilisateur(Long id) throws NotFoundException{
	    try {
	        Optional<Utilisateur> utilisateurOptional = utilisateurrepo.findById(id);
	        if (utilisateurOptional.isPresent()) {
	            Utilisateur utilisateur = utilisateurOptional.get();
	            utilisateur.setActivatedAccount(true);
	            utilisateurrepo.save(utilisateur);
	        } else {
	            // Handle the case when the user with the given id does not exist
	            throw new NotFoundException("User not found with id: " + id);
	        }
	    } catch (NotFoundException e) {
	        // Handle the exception 
	    	throw new NotFoundException("error " + e.getMessage());
	    }
	}

	@Override
	public Mono<Utilisateur> savefile(Utilisateur savedUtilisateur,FilePart file) {
	    if (!file.filename().isEmpty()) {
            String fileName = file.filename();
            String uploadDir = "src/main/resources/static/";
            Path destination = Paths.get(uploadDir).resolve(fileName);

            return file.transferTo(destination.toFile()).then(Mono.fromCallable(() -> {
                String imageUrl = "http://localhost:8060/images/" + fileName; // Assuming the endpoint to serve images is "/images"
                savedUtilisateur.setPhoto(imageUrl);
                utilisateurrepo.save(savedUtilisateur);
                return savedUtilisateur; // Return the saved Utilisateur object
            }));
        }   
	    return Mono.just(savedUtilisateur);
	}

}
