package com.wevioo.parametrage.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wevioo.parametrage.entities.Fond;

public interface FondService {

	 public Page <Fond> getAllFond(String search, int page, int size);
	 public void addFond(Fond fond );
	 public void deleteFond(Long id);
	 public void modifyFond(Fond fond);
	 public List <Fond> getAllFondofPartenaire(Long partenaireId);
	 public Fond getFondById(Long id);

}
