package com.wevioo.parametrage.servicesimpl;

import java.text.ParseException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import javax.validation.ValidationException;

import org.apache.commons.lang.enums.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Quotite;
import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.enums.Choix;
import com.wevioo.parametrage.repository.FondRepository;
import com.wevioo.parametrage.repository.QuotiteRepository;
import com.wevioo.parametrage.repository.ZoneRepository;
import com.wevioo.parametrage.services.QuotiteService;
import com.wevioo.parametrage.specification.QuotiteSpecification;

@Service
public class QuotiteServiceImpl implements QuotiteService{

	@Autowired
	private QuotiteRepository quotiteRepository;
	
	@Autowired
    private ZoneRepository zoneRepository;
	
	@Autowired
    private FondRepository fondRepository;
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Quotite> getAllQuotite(int page, int size, String fond, String zone, String zonal, String ritic,
	        String nouveauProm, String creditLeasing) throws NoSuchElementException {
	    try {
	        Pageable pageable = PageRequest.of(page, size);
			 QuotiteSpecification specif = new QuotiteSpecification();
	        Specification<Quotite> spec = specif.getSpec(fond, zone, zonal, ritic, nouveauProm, creditLeasing);
	        return quotiteRepository.findAll(spec, pageable);
	    } catch (Exception e) {
	        // Handle the exception
	        e.printStackTrace();
	        throw new NoSuchElementException("Failed to retrieve quotites: " + e.getMessage());
	    }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Quotite> getAllQuotite(int page, int size) throws NoSuchElementException {
	    try {
	        Pageable pageable = PageRequest.of(page, size);
	        return quotiteRepository.findAll(pageable);
	    } catch (Exception e) {
	        // Handle the exception
	        e.printStackTrace();
	        throw new NoSuchElementException("Failed to retrieve quotites: " + e.getMessage());
	    }
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addQuotite(Quotite quotite) throws ValidationException  {
	    try {
	    	validateaddQuotite(quotite);
	        Quotite quotitesaved = quotiteRepository.save(quotite);
	        Fond fond = fondRepository.findById(quotite.getFond().getIdFond()).orElse(null);
	        if (fond == null) {
	            throw new IllegalArgumentException("Fond with ID " + quotite.getFond().getIdFond() + " does not exist");
	        }
	        fond.getQuotites().add(quotitesaved);
	        quotitesaved.setFond(fond);
	        if (quotite.getZone() != null) {
	            quotitesaved.setZone(quotite.getZone());
	        }
	        fondRepository.save(fond);
	        quotiteRepository.save(quotitesaved);
	    } catch (ValidationException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
	    } catch (Exception e) {
	        // Handle the exception
	        e.printStackTrace();
	        throw new RuntimeException("Failed to add quotite: " + e.getMessage());
	    }
	}

	private <E extends Enum<E>> boolean isEnumValueValid(Class<E> enumClass, String value) {
	    return Arrays.stream(enumClass.getEnumConstants())
	            .map(Enum::name)
	            .anyMatch(name -> name.equals(value));
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Quotite getQuotiteById(Long id) throws NoSuchElementException {
	    try {
	        return quotiteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Quotite with ID " + id + " does not exist"));
	    } catch (Exception e) {
	        // Handle the exception
	        e.printStackTrace();
	        throw new NoSuchElementException("Failed to retrieve quotite: " + e.getMessage());
	    }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long modifyQuotite(Quotite quotite) throws ValidationException{
	    try {
	    	validatemodifyQuotite(quotite);
	        Quotite quotitesaved = quotiteRepository.findById(quotite.getIdQuotite()).orElseThrow(() -> new IllegalArgumentException("Quotite with ID " + quotite.getIdQuotite() + " does not exist"));
	        Fond fond = fondRepository.findById(quotite.getFond().getIdFond()).orElseThrow(() -> new IllegalArgumentException("Fond with ID " + quotite.getFond().getIdFond() + " does not exist"));
	        quotitesaved.setFond(fond);
	        quotitesaved.setRitic(quotite.getRitic());
	        quotitesaved.setValeurAppl(quotite.getValeurAppl());
	        quotitesaved.setZonal(quotite.getZonal());
	        quotitesaved.setCreditLeas(quotite.getCreditLeas());
	        if (quotite.getZone() != null) {
	            Zone zone = zoneRepository.findById(quotite.getZone().getIdZone()).orElseThrow(() -> new IllegalArgumentException("Zone with ID " + quotite.getZone().getIdZone() + " does not exist"));
	            quotitesaved.setZone(zone);
	        }
	        quotiteRepository.save(quotitesaved);
	        return quotitesaved.getIdQuotite();
	    }  catch (ValidationException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
	    }catch (Exception e) {
	        // Handle the exception
	        e.printStackTrace();
	        throw new RuntimeException("Failed to modify quotite: " + e.getMessage());
	    }
	}
	void validatemodifyQuotite(Quotite quotite) throws ValidationException {
	   try { // Validate non-nullable attributes
        if (quotite.getZonal() == null || quotite.getRitic() == null ||
                quotite.getNouvPromo() == null || quotite.getCreditLeas() == null ||
                quotite.getValeurAppl() == 0) {
            throw new IllegalArgumentException("Quotite attributes cannot be null or empty");
        }

        // Validate enumerated attribute values
        if (!isEnumValueValid(Choix.class, quotite.getZonal().name()) ||
                !isEnumValueValid(Choix.class, quotite.getRitic().name()) ||
                !isEnumValueValid(Choix.class, quotite.getNouvPromo().name()) ||
                !isEnumValueValid(Choix.class, quotite.getCreditLeas().name())) {
            throw new IllegalArgumentException("Invalid enumerated attribute value");
        }
	   } catch (IllegalArgumentException e) {
	        // Handle validation errors
	        throw new ValidationException("Validation error: " + e.getMessage());
	    } 
	}
	void validateaddQuotite(Quotite quotite) throws ValidationException {
	  try {  // Validate non-nullable attributes
        if (quotite.getZonal() == null || quotite.getRitic() == null ||
                quotite.getNouvPromo() == null || quotite.getCreditLeas() == null ||
                quotite.getValeurAppl() == 0) {
            throw new IllegalArgumentException("Quotite attributes cannot be null or empty");
        }

        // Validate enumerated attribute values
        if (!isEnumValueValid(Choix.class, quotite.getZonal().name()) ||
                !isEnumValueValid(Choix.class, quotite.getRitic().name()) ||
                !isEnumValueValid(Choix.class, quotite.getNouvPromo().name()) ||
                !isEnumValueValid(Choix.class, quotite.getCreditLeas().name())) {
            throw new IllegalArgumentException("Invalid enumerated attribute value");
        }
	} catch (IllegalArgumentException e) {
        // Handle validation errors
        throw new ValidationException("Validation error: " + e.getMessage());
    } 
	}

}
