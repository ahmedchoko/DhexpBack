package com.wevioo.demande.services;

import com.wevioo.demande.dto.DemandeDto;
import com.wevioo.demande.entities.Demande;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DemandeService {

	
	public boolean Verifmatriculefiscal(String matricule);
	public Demande createDemande(Demande demande);

	public List<Demande> getDemandes();
}
