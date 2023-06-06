package com.wevioo.parametrage.services;

import java.text.ParseException;
import java.util.List;
import org.springframework.data.domain.Page;
import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Partenaire;




public interface PartenaireService {

	
	
	 public Page <Partenaire> getAllPartenaire(String fond ,String modalite,String MontantMinsearchTerm,String MontantMaxsearchTerm, String StatutsearchTerm , int page, int size) throws ParseException;
	 public Long addPartenaire(Partenaire partenaire );
	  public Page <Partenaire> getPartenaireList(int page, int size) throws ParseException;
	 public Partenaire getPartenaireById(Long id);
	 public Long modifyPartenaire(Partenaire partenaire);
	public Convention addPartenairewithcvt(List <Convention> conventions);
	public Long modifyConvnetion(Long conventionId , String critere, String DateBlocage) throws ParseException;
	public Long modifyPartenairewithcvt(List<Convention> conventions);
	public List nobrePartenaireParType ();
	public List<Partenaire> getAllPartenaire ();
	public int nombreTotalPartenaire();
}
