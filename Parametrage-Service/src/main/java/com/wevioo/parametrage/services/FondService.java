package com.wevioo.parametrage.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Secteur;

public interface FondService {

	public Page <Fond> getAllFond(String date1 , String date2 ,String StatutsearchTerm,String MontantMaxsearchTerm,String MontantMinsearchTerm, int page, int size) throws ParseException;
	public Page <Fond> getAllFond(int page, int size) throws ParseException;
	public Fond addFond(Fond fond );
	public void deleteFond(Long id);
	public void modifyFond(Fond fond);
	public Map<Fond, List<Modalite>> getAllFondofPartenaire(Long partenaireId);
	public Fond getFondById(Long id);
	public List<Secteur> getAllSecteur ();
	public List<Fond> listFond();
	public List<Fond> getNonArchivedFonds();
    public List FondTresorieBySecteur();
    public List FondCountByStatus();
    public float FondTotal();
}

