package com.wevioo.parametrage.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;



public interface PartenaireService {

	
	
	 public Page <Partenaire> getAllPartenaire(String search, int page, int size);
	 public void addPartenaire(Partenaire partenaire );
	 public void deletePartenaire(Long id);
	 public void modifyPartenaire(Partenaire partenaire);
	 public List<Modalite> getAllModaliteOfpartenaire(Long partenaireId);
}
