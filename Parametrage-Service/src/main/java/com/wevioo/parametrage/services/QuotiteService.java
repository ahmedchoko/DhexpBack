package com.wevioo.parametrage.services;

import java.text.ParseException;


import org.springframework.data.domain.Page;

import com.wevioo.parametrage.entities.Quotite;

public interface QuotiteService {
	 public Page<Quotite> getAllQuotite(int page , int size , String fond ,String zone, String zonal , String ritic,String nouveauProm,String creditLeasing) throws ParseException;

	Page<Quotite> getAllQuotite(int page, int size) throws ParseException;

	public void addQuotite(Quotite quotite);

	public Quotite getQuotiteById(Long id);

	public Long modifyQuotite(Quotite quotite);
	
}
