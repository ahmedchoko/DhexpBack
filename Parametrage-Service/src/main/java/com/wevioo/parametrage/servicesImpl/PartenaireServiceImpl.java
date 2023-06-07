package com.wevioo.parametrage.servicesImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.repository.ConventionRepository;
import com.wevioo.parametrage.repository.PartenaireRepository;
import com.wevioo.parametrage.services.PartenaireService;
import com.wevioo.parametrage.specification.PartenaireSpecification;

/**
 * Service implementation for handling operations related to Partenaire entities.
 */
@Service
public class PartenaireServiceImpl implements PartenaireService {

	@Autowired
	private PartenaireRepository partenaireRepository;

	@Autowired
	private ConventionRepository conventionRepository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Partenaire> getAllPartenaire(String fond, String modalite, String montantMinsearchTerm,
	        String montantMaxsearchTerm, String statutsearchTerm, int page, int size) throws RuntimeException {
	    try {
	        Pageable pageable = PageRequest.of(page, size);
	        Specification<Partenaire> spec = PartenaireSpecification.getPartenaireWithSpec(fond, modalite, montantMinsearchTerm, montantMaxsearchTerm, statutsearchTerm);
	        Page<Partenaire> pats = partenaireRepository.findAll(spec, pageable);
	        for (Partenaire p : pats.getContent()) {
	            p.getConventions().size();
	        }
	        return pats;
	    } catch (Exception e) {
	        // Handle exceptions
	        throw new RuntimeException("Error while retrieving Partenaire list.", e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Partenaire addPartenaire(Partenaire partenaire) throws IllegalArgumentException {
	    try {
	    	validatePartenaire(partenaire);
	        // Save the partenaire
	        return partenaireRepository.save(partenaire);
	    } catch (IllegalArgumentException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
	    } catch (Exception e) {
	        // Handle other exceptions
	        throw new RuntimeException("Error while adding Partenaire.", e);
	    }
	}
	public void validatePartenaire(Partenaire partenaire) throws IllegalArgumentException {
        // Perform pattern validations
        String emailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
        String websitePattern = "^(http://|https://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
        String telephonePattern = "^(5[0-9]{7}|2[0-9]{7}|9[0-9]{7})$";
        String faxPattern = "^\\d{8}$";

        // Validate nullable attributes
        if (partenaire.getMail() == null || !partenaire.getMail().matches(emailPattern)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (partenaire.getSite() == null || !partenaire.getSite().matches(websitePattern)) {
            throw new IllegalArgumentException("Invalid website format.");
        }
        if (partenaire.getNumTelephone() == null || !partenaire.getNumTelephone().matches(telephonePattern)) {
            throw new IllegalArgumentException("Invalid telephone number format.");
        }
        if (!partenaire.getNumFax().matches(faxPattern)) {
            throw new IllegalArgumentException("Invalid fax number format.");
        }
        // Validate non-nullable attributes
        if (partenaire.getAdresse() == null || partenaire.getAdresse().isEmpty()) {
            throw new IllegalArgumentException("Adresse is required.");
        }
        if (partenaire.getStatut() == null) {
            throw new IllegalArgumentException("Statut is required.");
        }
        if (partenaire.getAdresse() == null || partenaire.getAdresse().isEmpty()) {
            throw new IllegalArgumentException("Adresse is required.");
        }
        if (partenaire.getTypePartenaire() == null) {
            throw new IllegalArgumentException("Type partenaire is required.");
        }
        if (partenaire.getAbrevPartenaire() == null) {
            throw new IllegalArgumentException("abrev Partenaire is required.");
        }
        if (partenaire.getDateActivation() == null) {
            throw new IllegalArgumentException("Date d'activation is required.");
        }
        if (partenaire.getDateBlocage() == null) {
            throw new IllegalArgumentException("Date Blocage is required.");
        }
        // Check uniqueness of attributes
        if (partenaire.getMail() != null) {
            Partenaire existingPartenaire = partenaireRepository.findByMailExceptId(partenaire.getMail(),partenaire.getIdPartenaire());
            if (existingPartenaire != null) {
                throw new IllegalArgumentException("Email must be unique.");
            }
        }

        if (partenaire.getSite() != null) {
            Partenaire existingPartenaire = partenaireRepository.findBySiteExceptId(partenaire.getSite(),partenaire.getIdPartenaire());
            if (existingPartenaire != null) {
                throw new IllegalArgumentException("Website must be unique.");
            }
        }

        if (partenaire.getNumTelephone() != null) {
            Partenaire existingPartenaire = partenaireRepository.findByNumTelephoneExceptId(partenaire.getNumTelephone(),partenaire.getIdPartenaire());
            if (existingPartenaire != null) {
                throw new IllegalArgumentException("Telephone number must be unique.");
            }
        }

        if (partenaire.getNumFax() != null) {
            Partenaire existingPartenaire = partenaireRepository.findByNumFaxExceptId(partenaire.getNumFax(),partenaire.getIdPartenaire());
            if (existingPartenaire != null) {
                throw new IllegalArgumentException("Fax number must be unique.");
            }
        }
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long modifyPartenaire(Partenaire partenaire) {
		try {
			validatePartenaire(partenaire);
	        // Save the partenaire
	        return partenaireRepository.save(partenaire).getIdPartenaire();
	    } catch (IllegalArgumentException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
	    } catch (Exception e) {
	        // Handle other exceptions
	        throw new RuntimeException("Error while modifying Partenaire.", e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Partenaire> getPartenaireList(int page, int size) {
	    try {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Partenaire> result = partenaireRepository.findAll(pageable);

	        if (result.isEmpty()) {
	            throw new RuntimeException("No data found for the requested page.");
	        }

	        return result;
	    } catch (Exception e) {
	        // Handle exceptions
	        throw new RuntimeException("Error while retrieving Partenaire list.", e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Convention addPartenairewithcvt(List<Convention> conventions)throws ValidationException {
	    try {
	        Convention conven = new Convention();
	        validatePartenaire (conventions.get(0).getPartenaire());
	        Partenaire partenaire = partenaireRepository.save(conventions.get(0).getPartenaire());
	        for (Convention convention : conventions) {
	            if (convention.getDateSignature() != null) {
	                convention.setPartenaire(partenaire);
	                convention.setDateSignature(convention.getDateSignature());
	                conven = conventionRepository.save(convention);
	            } else {
	            	partenaireRepository.deleteById(partenaire.getIdPartenaire());
	                // Handle the case when dateSignature is null
	                throw new IllegalArgumentException("dateSignature is null for a Convention. Cannot proceed with saving.");
	            }
	        }
	        return conven;
	    } catch (IllegalArgumentException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
	    } catch (Exception e) {
	        // Handle exceptions
	        throw new RuntimeException("Error while adding conventions with the associated Partenaire.", e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Partenaire getPartenaireById(Long id) {
	    try {
	        Optional<Partenaire> partenaireOptional = partenaireRepository.findById(id);
	        if (partenaireOptional.isPresent()) {
	            return partenaireOptional.get();
	        } else {
	            throw new IllegalArgumentException("Partenaire with the given ID does not exist.");
	        }
	    } catch (Exception e) {
	        // Handle exceptions
	        throw new RuntimeException("Error while retrieving Partenaire by ID: " + id, e);
	    }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long modifyConvention(Long conventionId, String critereModalite, String DateBlocage) throws ValidationException, RuntimeException{
	    try {
	        // Validate DateBlocage parameter
	        if (DateBlocage == null) {
	            throw new IllegalArgumentException("DateBlocage cannot be null.");
	        }
	        
	        // Parse the DateBlocage parameter
	        SimpleDateFormat f = new SimpleDateFormat("E MMM dd yyyy", Locale.ENGLISH);
	        Date dateBlocage = f.parse(DateBlocage);
	        
	        Convention convention = conventionRepository.findById(conventionId).orElseThrow(() ->
	            new IllegalArgumentException("Convention with the given ID does not exist.")
	        );
	        
	        Partenaire partenaire = convention.getPartenaire();
	        
	        // Validate critereModalite parameter
	        if (critereModalite == null) {
	            throw new IllegalArgumentException("critereModalite cannot be null.");
	        }
	        // Perform modifications
	        partenaire.setDateBlocage(dateBlocage);
	        partenaireRepository.save(partenaire);
	        
	        return convention.getIdConvention();
	    }  catch (IllegalArgumentException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
	    } 
	    catch (Exception e) {
	        // Handle exceptions
	        throw new RuntimeException("Error while modifying Convention with ID: " + conventionId, e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List nobrePartenaireParType() throws RuntimeException {
	    try {
	        return partenaireRepository.getNobrePartenaireParType();
	    } catch (Exception e) {
	        // Handle exceptions
	        throw new RuntimeException("Error while retrieving the number of partners by type.", e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int nombreTotalPartenaire() throws RuntimeException {
		try {
			return partenaireRepository.getNobreTotalPartenaire();

		}catch(Exception e) {
	        throw new RuntimeException("Error while retrieving the total number of partners .", e);

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Partenaire> getAllPartenaire() throws RuntimeException {
	    try {
	        List<Partenaire> partenaires = partenaireRepository.findAll();
	        
	        if (partenaires.isEmpty()) {
	            // Handle the case when the list is empty
	            throw new RuntimeException("No partners found.");
	        }   
	        return partenaires;
	    } catch (Exception e) {
	        // Handle other exceptions
	        throw new RuntimeException("Error while retrieving all partners.", e);
	    }
	}


}
