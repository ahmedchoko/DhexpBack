package com.wevioo.demande.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.wevioo.demande.dto.DemandePreliminaireDTO;
import com.wevioo.demande.entities.Demande;

public interface DemandeService {

	
	public boolean Verifmatriculefiscal(String matricule);
	public String VerifCritereEligibilite(DemandePreliminaireDTO demandePreliminaire);
	public  Page <Demande>  getDemandePreliminaire(int page, int size);
}
