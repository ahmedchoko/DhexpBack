package com.wevioo.parametrage.servicesImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.dto.FondDTO;
import com.wevioo.parametrage.entities.Activite;
import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.entities.Secteur;
import com.wevioo.parametrage.entities.SousSecteur;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.repository.ActiviteRepository;
import com.wevioo.parametrage.repository.ConventionRepository;
import com.wevioo.parametrage.repository.FondRepository;
import com.wevioo.parametrage.repository.PartenaireRepository;
import com.wevioo.parametrage.repository.SecteurRepository;
import com.wevioo.parametrage.repository.SousSecteurRepository;
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
	@Override
	public Page <Fond> getAllFond(String date1 ,String date2 ,String StatutsearchTerm,String MontantMaxsearchTerm,String MontantMinsearchTerm, int page, int size) throws ParseException {
		Pageable pageable = PageRequest.of(page, size);
		FondSpecification specif = new FondSpecification();
		Specification <Fond> spec = specif.getSpec(MontantMinsearchTerm, MontantMaxsearchTerm, StatutsearchTerm,date1, date2 );
		return fondRepository.findAll(spec,pageable);
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
	public Map<Fond, List<Modalite>> getAllFondofPartenaire(Long partenaireId) {
		Partenaire partenaire =this.partenaireRepository.findById(partenaireId).get();
		List <Convention> conventions = this.conventionRepository.findAll();
		List <Fond> fonds=new ArrayList<Fond>() ;
		List <Modalite> mods=new ArrayList<Modalite>() ;
		List < Object> test1=new ArrayList() ;
		Map  < Fond, Modalite > test = new HashMap <  Fond,Modalite> ();
		Map  < Fond, List <Modalite> > res = new HashMap <  Fond,List <Modalite>> ();
		/* for(Convention convention:conventions){
			 if(partenaire.getIdPartenaire()==convention.getPartenaire().getIdPartenaire()) {

				//System.out.println(convention.getModalite().getFond().toString());
				//System.out.println( convention.getModalite().toString());
				test.put(convention.getModalite().getFond(), convention.getModalite());
				test1.add(test);
				  fonds.add(convention.getModalite().getFond());
			 }

				    }
		 /*for (int i = 0; i < test1.size(); i++) {
			 for (int j=0 ; j < test1.size();j++) {
				 if(i!=j)  {
					   if( test1.get(i)== test1.get(j)) {
				 //fonds.remove(fonds.get(i));
				/// System.out.println( test1.get(i));
				 for (Map.Entry m : ((Map<Fond, Modalite>) test1.get(i)).entrySet()) {
					 for(Map.Entry nn : ((Map<Fond, Modalite>) test1.get(j)).entrySet()) {
			           System.out.println("ID: "+m.getKey()+", Nom: "+m.getValue());
			           if( m.getKey()== nn.getKey()) {
			            	//System.out.print( m.getKey().toString());
			            }

			        }
			 }


			 }

			}

			 }
*/
		System.out.println("dcd "+test1);
		for (Map.Entry<Fond, Modalite > m : test.entrySet()) {

			for (Map.Entry<Fond, Modalite > n : test.entrySet()) {
				if(m.getKey().getIdFond()==n.getKey().getIdFond()) {

					mods.add(m.getValue());
					res.put(m.getKey(),mods);
				}

			}

		}

		System.out.println("test111" + res);
		return res;
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


}

