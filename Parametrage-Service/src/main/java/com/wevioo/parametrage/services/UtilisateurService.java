package com.wevioo.parametrage.services;

import org.springframework.data.domain.Page;
import org.springframework.http.codec.multipart.FilePart;

import com.wevioo.parametrage.entities.Utilisateur;

import reactor.core.publisher.Mono;

public interface UtilisateurService {

	Utilisateur addUtilisateur(Utilisateur utilisateur);

	Utilisateur getUtilisateur(String utilisateurId);

	Page<Utilisateur> getAllUtilisateur(int page, int size);

	void setActivatedUtilisateur(Long id);

	Mono<Utilisateur> savefile(Utilisateur utilisateur ,FilePart file);

}
