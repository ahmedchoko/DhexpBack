package com.wevioo.parametrage.servicesImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Convention;
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
	 * Retrieves a page of Partenaire entities based on the specified search criteria.
	 * 
	 * @param fond             The fond search term
	 * @param modalite         The modalite search term
	 * @param MontantMinsearchTerm The minimum Montant search term
	 * @param MontantMaxsearchTerm The maximum Montant search term
	 * @param StatutsearchTerm  The Statut search term
	 * @param page             The page number
	 * @param size             The page size
	 * @return A page of Partenaire entities that match the search criteria
	 * @throws ParseException if there's an error parsing the search criteria
	 */
	@Override
	public Page<Partenaire> getAllPartenaire(String fond, String modalite, String MontantMinsearchTerm,
			String MontantMaxsearchTerm, String StatutsearchTerm, int page, int size) throws ParseException {

		Pageable pageable = PageRequest.of(page, size);

		PartenaireSpecification specif = new PartenaireSpecification();
		Specification<Partenaire> spec = specif.getPartenaireWithSpec(fond, modalite, MontantMinsearchTerm,
				MontantMaxsearchTerm, StatutsearchTerm);
		Page<Partenaire> pats = partenaireRepository.findAll(spec, pageable);
		for (Partenaire p : pats.getContent()) {
			p.getConventions().size();
		}
		return pats;
	}

	/**
	 * Adds a new Partenaire entity.
	 * 
	 * @param partenaire The Partenaire entity to add
	 * @return The ID of the newly added Partenaire entity
	 */
	@Override
	public Long addPartenaire(Partenaire partenaire) {
		return partenaireRepository.save(partenaire).getIdPartenaire();
	}


	/**
	 * Modifies a Partenaire entity.
	 * 
	 * @param partenaire The modified Partenaire entity
	 * @return The ID of the modified Partenaire entity
	 */
	@Override
	public Long modifyPartenaire(Partenaire partenaire) {
		return partenaireRepository.save(partenaire).getIdPartenaire();
	}

	/**
	 * Retrieves a page of Partenaire entities.
	 * 
	 * @param page The page number
	 * @param size The page size
	 * @return A page of Partenaire entities
	 * @throws ParseException if there's an error parsing the search criteria
	 */
	@Override
	public Page<Partenaire> getPartenaireList(int page, int size) throws ParseException {
		Pageable pageable = PageRequest.of(page, size);
		return partenaireRepository.findAll(pageable);
	}

	/**
	 * Adds a new Partenaire entity with the given list of conventions.
	 * 
	 * @param conventions The list of conventions to associate with the Partenaire
	 *                    entity
	 * @return The Partenaire entity with the added conventions
	 */
	@Override
	public Convention addPartenairewithcvt(List<Convention> conventions) {
		Convention conven = new Convention();
		Partenaire partenaire = partenaireRepository.save(conventions.stream().findFirst().get().getPartenaire());
		for (Convention convention : conventions) {
			convention.setPartenaire(partenaire);
			convention.setDateSignature(convention.getDateSignature());
			conven = conventionRepository.save(convention);
		}
		return conven;
	}

	/**
	 * Retrieves a Partenaire entity by its ID.
	 * 
	 * @param id The ID of the Partenaire entity to retrieve
	 * @return The Partenaire entity with the specified ID
	 */
	@Override
	public Partenaire getPartenaireById(Long id) {
		return partenaireRepository.findById(id).get();
	}

	/**
	 * Modifies a Partenaire entity with the given list of conventions.
	 * 
	 * @param conventions The list of conventions to associate with the Partenaire
	 *                    entity
	 * @return The ID of the modified Partenaire entity
	 */
	@Override
	public Long modifyPartenairewithcvt(List<Convention> conventions) {
		Partenaire p = partenaireRepository.save(conventions.stream().findFirst().get().getPartenaire());
		for (Convention convention : conventions) {
			convention.setPartenaire(p);
			convention.setDateSignature(convention.getDateSignature());
			conventionRepository.save(convention);
		}
		return p.getIdPartenaire();
	}

	/**
	 * Modifies a Convention entity by its ID.
	 * 
	 * @param conventionId     The ID of the Convention entity to modify
	 * @param critereModalite  The modified critereModalite value
	 * @param DateBlocage      The modified DateBlocage value
	 * @return The ID of the modified Convention entity
	 * @throws ParseException if there's an error parsing the DateBlocage value
	 */
	@Override
	public Long modifyConvnetion(Long conventionId, String critereModalite, String DateBlocage)
			throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("E MMM dd yyyy", Locale.ENGLISH);
		Date date1re = f.parse(DateBlocage);
		Convention convention = conventionRepository.findById(conventionId).get();
		Partenaire partenaire = partenaireRepository.findById(convention.getPartenaire().getIdPartenaire()).get();
		partenaire.setDateBlocage(date1re);
		partenaireRepository.save(partenaire);
		return convention.getIdConvention();
	}

	/**
	 * Retrieves the number of partenaires per type.
	 * 
	 * @return A list containing the number of partenaires per type
	 */
	@Override
	public List nobrePartenaireParType() {
		return partenaireRepository.getNobrePartenaireParType();
	}

	/**
	 * Retrieves the total number of partenaires.
	 * 
	 * @return The total number of partenaires
	 */
	@Override
	public int nombreTotalPartenaire() {
		return partenaireRepository.getNobreTotalPartenaire();
	}

	/**
	 * Retrieves all Partenaire entities.
	 * 
	 * @return A list of all Partenaire entities
	 */
	@Override
	public List<Partenaire> getAllPartenaire() {
		return partenaireRepository.findAll();
	}

}
