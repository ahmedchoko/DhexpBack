package com.wevioo.parametrage.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Utilisateur;
import com.wevioo.parametrage.repository.UtilisateurRepository;
import com.wevioo.parametrage.services.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{

	@Autowired
	private UtilisateurRepository utilisateurrepo ;
	
	
	@Override
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		return utilisateurrepo.save(utilisateur);
		
	}
	
	@Override
	public Utilisateur getUtilisateur(String utilisateurId) {
		Utilisateur utilisateur = null ; 
		for(Utilisateur util : utilisateurrepo.findAll()) {
			if(util.getUserkeycloakId().equals(utilisateurId)) {
				utilisateur=util ; 
			}
				
		}
		return utilisateur;
		
	}

	@Override
	public Page<Utilisateur> getAllUtilisateur(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return utilisateurrepo.findAll(pageable);
	}

	@Override
	public void setActivatedUtilisateur(Long id) {
		Utilisateur utilisateur = utilisateurrepo.findById(id).get();
		utilisateur.setActivatedAccount(true);
		utilisateurrepo.save(utilisateur);
	}
}
