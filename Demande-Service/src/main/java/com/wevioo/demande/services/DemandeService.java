package com.wevioo.demande.services;

import java.text.ParseException;
import java.util.List;

import com.wevioo.demande.dto.DemandeDto;
import org.springframework.data.domain.Page;

import com.wevioo.demande.dto.DemandePreliminaireDTO;
import com.wevioo.demande.entities.Demande;

public interface DemandeService {


	Demande createDemande(Demande demande);

	public Page<Demande> getDemandes(int page, int size);
	public boolean Verifmatriculefiscal(String matricule);
	public String VerifCritereEligibilite(DemandePreliminaireDTO demandePreliminaire);
	public  Page <Demande>  getDemandePreliminaire(int page, int size);
	Demande createDemande(DemandePreliminaireDTO demande);
	public Demande updateDemande(DemandeDto demande) throws ParseException;
}
