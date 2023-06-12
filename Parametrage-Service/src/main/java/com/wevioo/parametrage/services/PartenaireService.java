package com.wevioo.parametrage.services;

import java.text.ParseException;
import java.util.List;
import org.springframework.data.domain.Page;

import com.wevioo.parametrage.dto.ConventionDTO;
import com.wevioo.parametrage.dto.PartenaireDTO;
import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Partenaire;




public interface PartenaireService {

	

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
	
	 public Page <Partenaire> getAllPartenaire(String fond ,String modalite,String MontantMinsearchTerm,String MontantMaxsearchTerm, String StatutsearchTerm , int page, int size) throws ParseException;
	 
		/**
		 * Adds a new Partenaire entity.
		 * 
		 * @param partenaire The Partenaire entity to add
		 * @return The ID of the newly added Partenaire entity
		 */
	 public Partenaire addPartenaire(PartenaireDTO partenaire );
		/**
		 * Retrieves a page of Partenaire entities.
		 * 
		 * @param page The page number
		 * @param size The page size
		 * @return A page of Partenaire entities
		 * @throws ParseException if there's an error parsing the search criteria
		 */
		
	  public Page <Partenaire> getPartenaireList(int page, int size) throws ParseException;
		/**
		 * Retrieves a Partenaire entity by its ID.
		 * 
		 * @param id The ID of the Partenaire entity to retrieve
		 * @return The Partenaire entity with the specified ID
		 */	
	 public Partenaire getPartenaireById(Long id);
		/**
		 * Modifies a Partenaire entity.
		 * 
		 * @param partenaire The modified Partenaire entity
		 * @return The ID of the modified Partenaire entity
		 */
		
	 public Partenaire modifyPartenaire(PartenaireDTO partenaire);

		/**
		 * Adds a new Partenaire entity with the given list of conventions.
		 * 
		 * @param conventions The list of conventions to associate with the Partenaire
		 *                    entity
		 * @return The Partenaire entity with the added conventions
		 */
		
	public Convention addPartenairewithcvt(ConventionDTO convention);
	/**
	 * Modifies a Convention entity by its ID.
	 * 
	 * @param conventionId     The ID of the Convention entity to modify
	 * @param critereModalite  The modified critereModalite value
	 * @param DateBlocage      The modified DateBlocage value
	 * @return The ID of the modified Convention entity
	 * @throws ParseException if there's an error parsing the DateBlocage value
	 */
	public Long modifyConvention(Long conventionId, String DateBlocage) throws ParseException;
	

	/**
	 * Retrieves the number of partenaires per type.
	 * 
	 * @return A list containing the number of partenaires per type
	 */
	public List nobrePartenaireParType ();
	/**
	 * Retrieves all Partenaire entities.
	 * 
	 * @return A list of all Partenaire entities
	 */
	public List<Partenaire> getAllPartenaire ();

	/**
	 * Retrieves the total number of partenaires.
	 * 
	 * @return The total number of partenaires
	 */
	
	public int nombreTotalPartenaire();

	public Partenaire getPartenaireByAbrv(String abrv);
}
