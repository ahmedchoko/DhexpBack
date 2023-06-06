package com.wevioo.parametrage.servicesImpl;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Quotite;
import com.wevioo.parametrage.entities.Zone;
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
	 * Retrieves a page of Quotite entities based on the provided search criteria.
	 * 
	 * @param page          The page number
	 * @param size          The page size
	 * @param fond          The fond search term
	 * @param zone          The zone search term
	 * @param zonal         The zonal search term
	 * @param ritic         The ritic search term
	 * @param nouveauProm   The nouveauProm search term
	 * @param creditLeasing The creditLeasing search term
	 * @return A page of Quotite entities matching the search criteria
	 * @throws ParseException if there's an error parsing the search criteria
	 */
	@Override
	public Page<Quotite> getAllQuotite(int page, int size, String fond, String zone, String zonal, String ritic,
			String nouveauProm, String creditLeasing) throws ParseException {
		Pageable pageable = PageRequest.of(page, size);
		QuotiteSpecification specif = new QuotiteSpecification();
		Specification<Quotite> spec = specif.getSpec(fond, zone, zonal, ritic, nouveauProm, creditLeasing);
		return quotiteRepository.findAll(spec, pageable);
	}

	/**
	 * Retrieves a page of all Quotite entities.
	 * 
	 * @param page The page number
	 * @param size The page size
	 * @return A page of Quotite entities
	 * @throws ParseException if there's an error parsing the search criteria
	 */
	@Override
	public Page<Quotite> getAllQuotite(int page, int size) throws ParseException {
		Pageable pageable = PageRequest.of(page, size);
		return quotiteRepository.findAll(pageable);
	}

	/**
	 * Adds a new Quotite entity.
	 * 
	 * @param quotite The Quotite entity to add
	 */
	@Override
	public void addQuotite(Quotite quotite) {
		Quotite quotitesaved = quotiteRepository.save(quotite);
		Fond fond = fondRepository.findById(quotite.getFond().getIdFond()).get();
		fond.getQuotites().add(quotitesaved);
		quotitesaved.setFond(fond);
		if (quotite.getZone() != null) {
			quotitesaved.setZone(quotite.getZone());
		}
		fondRepository.save(fond);
		quotiteRepository.save(quotitesaved);
	}

	/**
	 * Retrieves a Quotite entity by its ID.
	 * 
	 * @param id The ID of the Quotite entity
	 * @return The Quotite entity
	 */
	@Override
	public Quotite getQuotiteById(Long id) {
		return quotiteRepository.findById(id).get();
	}

	/**
	 * Modifies an existing Quotite entity.
	 * 
	 * @param quotite The modified Quotite entity
	 * @return The ID of the modified Quotite entity
	 */
	@Override
	public Long modifyQuotite(Quotite quotite) {
		Quotite quotitesaved = quotiteRepository.findById(quotite.getIdQuotite()).get();
		Fond fond = fondRepository.findById(quotite.getFond().getIdFond()).get();
		quotitesaved.setFond(fond);
		if (quotite.getZone() != null) {
			Zone zone = zoneRepository.findById(quotite.getZone().getIdZone()).get();
			quotitesaved.setZone(zone);
		}
		quotiteRepository.save(quotitesaved);
		return quotitesaved.getIdQuotite();
	}
}
