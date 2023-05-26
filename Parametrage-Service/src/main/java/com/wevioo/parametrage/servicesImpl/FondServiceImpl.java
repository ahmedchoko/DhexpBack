package com.wevioo.parametrage.servicesImpl;


import java.text.ParseException;

import java.util.HashSet;
import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;


import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import com.wevioo.parametrage.entities.Activite;

import com.wevioo.parametrage.entities.Fond;

import com.wevioo.parametrage.entities.Secteur;

import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.repository.ActiviteRepository;
import com.wevioo.parametrage.repository.ConventionRepository;
import com.wevioo.parametrage.repository.FondRepository;
import com.wevioo.parametrage.repository.PartenaireRepository;
import com.wevioo.parametrage.repository.SecteurRepository;

import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.specification.FondSpecification;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class FondServiceImpl implements FondService{
	@Autowired
	private FondRepository fondRepository;
	@Autowired
	private PartenaireRepository partenaireRepository;
	@Autowired
	private ConventionRepository conventionRepository;
	@Autowired
	private SecteurRepository secteurRepository;
	@Autowired
	private ActiviteRepository activiteRepository;
	@Override
	public Page <Fond> getAllFond(String date1 ,String date2 ,String StatutsearchTerm,String MontantMaxsearchTerm,String MontantMinsearchTerm, int page, int size) throws ParseException {
		Pageable pageable = PageRequest.of(page, size);
		//FondSpecification specif = new FondSpecification();
		Specification <Fond> spec = FondSpecification.getSpec(MontantMinsearchTerm, MontantMaxsearchTerm, StatutsearchTerm,date1, date2 );
		Page <Fond> fonds =fondRepository.findAll(spec,pageable);
				for (Fond fond : fonds.getContent()){
					fond.getSecteurs().size();
				};
		return fonds;
	}
	@Transactional
	@Override
	public Fond addFond(Fond fond) {
		Fond fond1 = new Fond();
		fond1.setMontantMax(fond.getMontantMax());
		fond1.setMontantMin(fond.getMontantMin());
		fond1.setDateClotureFond(fond.getDateClotureFond());
		fond1.setDateDemarrageFond(fond.getDateDemarrageFond());
		fond1.setStatut(fond.getStatut());
		fond1.setAbrevFond(fond.getAbrevFond());
		fond1.setNomArabeFond(fond.getNomArabeFond());
		fond1.setNomCompletFond(fond.getNomCompletFond());
		fond1.setNomFond(fond.getNomFond());
		fond1.setTresorerieFond(fond.getTresorerieFond());
		Set<Secteur> secteurs = fond.getSecteurs();
		fond.setSecteurs(null);
		Fond newFond = fondRepository.save(fond);
		if (secteurs != null && !secteurs.isEmpty()) {
			Set<Secteur> secteursmo = new HashSet<>();
			for (Secteur secteur : secteurs) {
				Secteur existingSecteur = secteurRepository.findById(secteur.getIdSec()).orElse(null);
				if (existingSecteur != null) {
					secteursmo.add(existingSecteur);
				}
			}
			newFond.setSecteurs(secteursmo);
			newFond = fondRepository.save(newFond);
		}
		return newFond;
	}






	@Override
	public void deleteFond(Long id) {
		Fond fond = fondRepository.getById(id);
		fond.setStatut(Fondstatut.ARCHIVE);
		fondRepository.save(fond);
	}

	@Transactional
	@Override
	public void modifyFond(Fond fond) {
      /*  Fond fond2 = fondRepository.findById(fond.getIdFond()).get();
        System.out.println(fond.getSecteurs().stream().findFirst().get().toString());
		fond2.setMontantMax(fond.getMontantMax());
		fond2.setMontantMin(fond.getMontantMin());
		fond2.setDateClotureFond(fond.getDateClotureFond());
		fond2.setDateDemarrageFond(fond.getDateDemarrageFond());
		fond2.setStatut(fond.getStatut());
		fond2.setAbrevFond(fond.getAbrevFond());
		fond2.setNomArabeFond(fond.getNomArabeFond());
		fond2.setNomCompletFond(fond.getNomCompletFond());
		fond2.setNomFond(fond.getNomFond());
		fond2.setTresorerieFond(fond.getTresorerieFond());*/
		Set<Secteur> secteurs = new HashSet<>();
		for (Secteur secteurInput : fond.getSecteurs()) {
			secteurs.add(secteurRepository.findById(secteurInput.getIdSec()).get());
		}

		// Update the Set of Secteur entities in the Fond entity
		fond.setSecteurs(secteurs);

		// Save the modified Fond entity
		fondRepository.save(fond);
	}
	@Override
	public Fond getFondById(Long id) {
		Fond fond = fondRepository.findById(id).get();
		fond.getModalites();
		return fond;
	}

	@Override
	public Page<Fond> getAllFond(int page, int size) throws ParseException {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);
		return fondRepository.findAll(pageable);
	}
	@Override
	public List<Secteur> getAllSecteur() {
		
		return secteurRepository.findAll().stream().collect(Collectors.toList());
	}
	@Override
	public List<Fond> listFond() {
		// TODO Auto-generated method stub
		return fondRepository.findAll();
	}
	@Override
	public List<Fond> getNonArchivedFonds(){
		return fondRepository.getNonArchivedFonds();
	}
	@Override
	public List fondTresorieBySecteur() {
		// TODO Auto-generated method stub
		return fondRepository.FondTresorieBySecteur();
	}
	@Override
	public List fondCountByStatus() {
		// TODO Auto-generated method stub
		return fondRepository.FondCountByStatus();
	}
	@Override
	public float fondTotal() {
		// TODO Auto-generated method stub
		return fondRepository.FondTotal();
	}
	@Override
	public List<Activite> listActivites() {
		// TODO Auto-generated method stub
		return activiteRepository.findAll();
	}


}

