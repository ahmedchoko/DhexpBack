package com.wevioo.parametrage.services;

import org.springframework.data.domain.Page;

import com.wevioo.parametrage.entities.Utilisateur;

public interface UtilisateurService {

	Utilisateur addUtilisateur(Utilisateur utilisateur);

	Utilisateur getUtilisateur(String utilisateurId);

	Page<Utilisateur> getAllUtilisateur(int page, int size);

	void setActivatedUtilisateur(Long id);

}
