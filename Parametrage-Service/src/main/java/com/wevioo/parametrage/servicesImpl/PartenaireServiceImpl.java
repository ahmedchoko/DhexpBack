package com.wevioo.parametrage.servicesimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.dto.ConventionDTO;
import com.wevioo.parametrage.dto.PartenaireDTO;
import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.repository.ConventionRepository;
import com.wevioo.parametrage.repository.ModaliteRepository;
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
	private ModaliteRepository modaliteRepository;

	@Autowired
	private ConventionRepository conventionRepository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Partenaire> getAllPartenaire(String fond, String modalite, String montantMinsearchTerm,
	        String montantMaxsearchTerm, String statutsearchTerm, int page, int size) throws NoSuchElementException {
	    try {
	        Pageable pageable = PageRequest.of(page, size);
	        Specification<Partenaire> spec = PartenaireSpecification.getPartenaireWithSpec(fond, modalite, montantMinsearchTerm, montantMaxsearchTerm, statutsearchTerm);
	        return partenaireRepository.findAll(spec, pageable);
	    } catch (Exception e) {
	        // Handle exceptions
	        throw new NoSuchElementException("Error while retrieving Partenaire list.", e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Partenaire addPartenaire(PartenaireDTO partenairedto) {
	    try {
	    	validateaddPartenaire(partenairedto);
	        // Save the partenaire
			Partenaire partenaire = Partenaire.builder().abrevPartenaire(partenairedto.getAbrevPartenaire()).adresse(partenairedto.getAdresse()).codeBic(partenairedto.getCodeBic()).dateActivation(partenairedto.getDateActivation()).dateBlocage(partenairedto.getDateBlocage()).mail(partenairedto.getMail()).nomArabePartenaire(partenairedto.getNomArabePartenaire()).nomCompletPartenaire(partenairedto.getNomCompletPartenaire()).site(partenairedto.getSite()).statut(partenairedto.getStatut()).numTelephone(partenairedto.getNumTelephone().toString()).numFax(partenairedto.getNumFax().toString()).typePartenaire(partenairedto.getTypePartenaire()).build();
			return partenaireRepository.save(partenaire);
	    } catch (IllegalArgumentException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
	    } 
	    }
	public void validateaddPartenaire(PartenaireDTO partenaireDTO) throws IllegalArgumentException {
        // Perform pattern validations
        String emailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
        String websitePattern = "^(http://|https://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
        String telephonePattern = "^(5[0-9]{7}|2[0-9]{7}|9[0-9]{7})$";
        String faxPattern = "^\\d{8}$";

        // Validate non nullable and format of attributes
        if (partenaireDTO.getMail() == null || !partenaireDTO.getMail().matches(emailPattern)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (partenaireDTO.getSite() == null || !partenaireDTO.getSite().matches(websitePattern)) {
            throw new IllegalArgumentException("Invalid website format.");
        }
        if (partenaireDTO.getNumTelephone() == null || !partenaireDTO.getNumTelephone().matches(telephonePattern)) {
            throw new IllegalArgumentException("Invalid telephone number format.");
        }
        if (!partenaireDTO.getNumFax().matches(faxPattern)) {
            throw new IllegalArgumentException("Invalid fax number format.");
        }
        if (partenaireDTO.getAdresse() == null || partenaireDTO.getAdresse().isEmpty()) {
            throw new IllegalArgumentException("Adresse is required.");
        }
        if (partenaireDTO.getTypePartenaire() == null) {
            throw new IllegalArgumentException("Type partenaire is required.");
        }
        if (partenaireDTO.getAbrevPartenaire() == null) {
            throw new IllegalArgumentException("abrev Partenaire is required.");
        }
        // Check uniqueness of attributes
        if (partenaireDTO.getMail() != null) {
            Partenaire existingPartenaire = partenaireRepository.findByMail(partenaireDTO.getMail());
            if (existingPartenaire != null) {

                throw new IllegalArgumentException("Email must be unique.");
            }
        }

        if (partenaireDTO.getSite() != null) {
            Partenaire existingPartenaire = partenaireRepository.findBySite(partenaireDTO.getSite());
            if (existingPartenaire != null) {
                throw new IllegalArgumentException("Website must be unique.");
            }
        }

        if (partenaireDTO.getNumTelephone() != null) {
            Partenaire existingPartenaire = partenaireRepository.findByNumTelephone(partenaireDTO.getNumTelephone());
            if (existingPartenaire != null) {
                throw new IllegalArgumentException("Telephone number must be unique.");
            }
        }

        if (partenaireDTO.getNumFax() != null) {
            Partenaire existingPartenaire = partenaireRepository.findByNumFax(partenaireDTO.getNumFax());
            if (existingPartenaire != null) {   
                throw new IllegalArgumentException("Fax number must be unique.");
            }
        }
	}
	public void validatemodifyPartenaire(PartenaireDTO partenaire) throws ValidationException {
        // Perform pattern validations
        String emailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
        String websitePattern = "^(http://|https://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
        String telephonePattern = "^(5[0-9]{7}|2[0-9]{7}|9[0-9]{7})$";
        String faxPattern = "^\\d{8}$";
try {
        // Validate non nullable and format of attributes
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
        if (partenaire.getTypePartenaire() == null) {
            throw new IllegalArgumentException("Type partenaire is required.");
        }
        if (partenaire.getAbrevPartenaire() == null) {
            throw new IllegalArgumentException("abrev Partenaire is required.");
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
        if (partenaire.getAbrevPartenaire() != null) {
            Partenaire existingPartenaire = partenaireRepository.findByAbrevPartenaireExceptId(partenaire.getAbrevPartenaire(),partenaire.getIdPartenaire());
            if (existingPartenaire != null) {
                throw new IllegalArgumentException("Abrev Partenaire must be unique.");
            }
        }
}
        catch (IllegalArgumentException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Partenaire modifyPartenaire(PartenaireDTO partenairedto) {
		try {
			validatemodifyPartenaire(partenairedto);
			Partenaire partenaire = Partenaire.builder().idPartenaire(partenairedto.getIdPartenaire()).abrevPartenaire(partenairedto.getAbrevPartenaire()).adresse(partenairedto.getAdresse()).codeBic(partenairedto.getCodeBic()).dateActivation(partenairedto.getDateActivation()).dateBlocage(partenairedto.getDateBlocage()).mail(partenairedto.getMail()).nomArabePartenaire(partenairedto.getNomArabePartenaire()).nomCompletPartenaire(partenairedto.getNomCompletPartenaire()).site(partenairedto.getSite()).statut(partenairedto.getStatut()).numTelephone(partenairedto.getNumTelephone().toString()).numFax(partenairedto.getNumFax().toString()).typePartenaire(partenairedto.getTypePartenaire()).build();
	        // Save the partenaire
	        return partenaireRepository.save(partenaire);
	    } catch (ValidationException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
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
	            throw new NoSuchElementException("No data found for the requested page.");
	        }

	        return result;
	    } catch (Exception e) {
	        // Handle exceptions
	        throw new NoSuchElementException("Error while retrieving Partenaire list.", e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Convention addPartenairewithcvt(ConventionDTO convention)throws ValidationException {
	    try {
	    	Partenaire partenaire;
            Convention conven = new Convention();
	        if(convention.getPartenaire().getIdPartenaire()==null) {
		        validateaddPartenaire (convention.getPartenaire());
		         partenaire =addPartenaire(convention.getPartenaire());
	        }
	        else {
		        validatemodifyPartenaire (convention.getPartenaire());
		         partenaire = modifyPartenaire(convention.getPartenaire());
	        }
	            if (convention.getDateSignature() != null) {
          			 Modalite modalite =  modaliteRepository.findById(convention.getModalite().getIdModalite()).get();
		            // handle the case where date start fond selected related to the new convention is > date end of the previous fond affected to the partner
            if(!convention.getPartenaire().getConventions().isEmpty()) {
	            	for(Convention conventi : convention.getPartenaire().getConventions()) {
  	                if(modalite.getFond().getDateDemarrageFond().before(conventi.getModalite().getFond().getDateClotureFond()) && !modalite.getFond().getIdFond().equals(conventi.getModalite().getFond().getIdFond())) {
		                throw new IllegalArgumentException("date start fond selected related to the new convention must be after date end of the previous fonds affected to the partner");
	                }
	                else {
	                conven.setPartenaire(partenaire);
	            	conven.setDateSignature(convention.getDateSignature());
	            	conven.setModalite(modalite);
	                conven = conventionRepository.save(conven);
	                }
	            }
	            }
	            
	            /// add convention to Partenaire
	            else {
	                conven.setPartenaire(partenaire);
	            	conven.setDateSignature(convention.getDateSignature());
	            	conven.setModalite(modalite);
	                conven = conventionRepository.save(conven);
	            }
	            }
	            else {
	            	partenaireRepository.deleteById(partenaire.getIdPartenaire());
	                // Handle the case when dateSignature is null
	                throw new IllegalArgumentException("dateSignature is null for a Convention. Cannot proceed with saving.");
	            }
	        
	        return conven;
	    } catch (IllegalArgumentException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
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
	    } catch (IllegalArgumentException e) {
	        // Handle exceptions
	        throw new IllegalArgumentException("Error while retrieving Partenaire by ID: " + id, e);
	    }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long modifyConvention(Long conventionId, String dateBlocage) throws ValidationException{
	    try {
	        // Validate DateBlocage parameter
	        if (dateBlocage == null) {
	            throw new IllegalArgumentException("DateBlocage cannot be null.");
	        }
	        
	        // Parse the DateBlocage parameter
	        SimpleDateFormat f = new SimpleDateFormat("E MMM dd yyyy", Locale.ENGLISH);
	        
	        Date dateBlocageparsed = f.parse(dateBlocage);
	        Convention convention = conventionRepository.findById(conventionId).orElseThrow(() ->
	             new IllegalArgumentException("Convention with the given ID does not exist.")
	        );
	        
	        Partenaire partenaire = convention.getPartenaire();
	        
            // handle the case where date blocage convention > date cloture fond du partenaire

	         if(dateBlocageparsed.after(convention.getModalite().getFond().getDateClotureFond())) {
	        	 throw new IllegalArgumentException(" Validation error: DateClotureFond must be after date Blocage Convention");
	         }
	        // Perform modifications
	        partenaire.setDateBlocage(dateBlocageparsed);
	        partenaireRepository.save(partenaire);
	        
	        return convention.getIdConvention();
	    }  catch (IllegalArgumentException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
	    } catch (ParseException e) {
			e.printStackTrace();
		}
		return conventionId; 
	  
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List nobrePartenaireParType() throws NoSuchElementException {
	    try {
	        return partenaireRepository.getNobrePartenaireParType();
	    } catch (Exception e) {
	        // Handle exceptions
	        throw new NoSuchElementException("Error while retrieving the number of partners by type.", e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int nombreTotalPartenaire() throws NoSuchElementException {
		try {
			return partenaireRepository.getNobreTotalPartenaire();

		}catch(Exception e) {
	        throw new NoSuchElementException("Error while retrieving the total number of partners .", e);

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Partenaire> getAllPartenaire() {
	    try {
	        List<Partenaire> partenaires = partenaireRepository.findAll();
	        
	        if (partenaires.isEmpty()) {
	            // Handle the case when the list is empty
	            throw new NoSuchElementException("No partners found.");
	        }   
	        return partenaires;
	    } catch (Exception e) {
	        // Handle other exceptions
	        throw new NoSuchElementException("Error while retrieving all partners.", e);
	    }
	}
	@Override
	public Partenaire getPartenaireByAbrv(String abrv) {
		return partenaireRepository.findByAbrevPartenaire(abrv);
	}


}
