package com.wevioo.parametrage.services;

import java.text.ParseException;


import org.springframework.data.domain.Page;

import com.wevioo.parametrage.entities.Quotite;

public interface QuotiteService {
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
	 public Page<Quotite> getAllQuotite(int page , int size , String fond ,String zone, String zonal , String ritic,String nouveauProm,String creditLeasing) throws ParseException;
		/**
		 * Retrieves a page of all Quotite entities.
		 * 
		 * @param page The page number
		 * @param size The page size
		 * @return A page of Quotite entities
		 * @throws ParseException if there's an error parsing the search criteria
		 */
	Page<Quotite> getAllQuotite(int page, int size) throws ParseException;
	/**
	 * Adds a new Quotite entity.
	 * 
	 * @param quotite The Quotite entity to add
	 */
	public void addQuotite(Quotite quotite);
	/**
	 * Retrieves a Quotite entity by its ID.
	 * 
	 * @param id The ID of the Quotite entity
	 * @return The Quotite entity
	 */
	public Quotite getQuotiteById(Long id);
	/**
	 * Modifies an existing Quotite entity.
	 * 
	 * @param quotite The modified Quotite entity
	 * @return The ID of the modified Quotite entity
	 */
	public Long modifyQuotite(Quotite quotite);
	
}
