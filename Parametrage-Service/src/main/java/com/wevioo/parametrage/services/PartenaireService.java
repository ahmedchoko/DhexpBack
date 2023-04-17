package com.wevioo.parametrage.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;



public interface PartenaireService {

	
	
	 public Page <Partenaire> getAllPartenaire(String fond ,String modalite,String MontantMinsearchTerm,String MontantMaxsearchTerm, String StatutsearchTerm , int page, int size) throws ParseException;
	 public void addPartenaire(Partenaire partenaire );
	  public Page <Partenaire> getPartenaireList(int page, int size) throws ParseException;
	 public void deletePartenaire(Long id);
	 public Partenaire getPartenaireById(Long id);
	 public void modifyPartenaire(Partenaire partenaire);
	 public List<Modalite> getAllModaliteOfpartenaire(Long partenaireId);
	public Convention addPartenairewithcvt(List <Convention> conventions);
	public void modifyPartenairewithcvt(List<Convention> conventions);
}
