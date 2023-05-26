package com.wevioo.parametrage.servicesImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mysql.cj.Session;
import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.enums.TypeDemande;
import com.wevioo.parametrage.enums.TypePatenaire;
import com.wevioo.parametrage.repository.ConventionRepository;
import com.wevioo.parametrage.repository.ModaliteRepository;
import com.wevioo.parametrage.repository.PartenaireRepository;
import com.wevioo.parametrage.services.PartenaireService;
import com.wevioo.parametrage.specification.FondSpecification;
import com.wevioo.parametrage.specification.PartenaireSpecification;


@Service
public class PartenaireServiceImpl implements PartenaireService{

	  @Autowired
	    private EntityManager entityManager;
	@Autowired
	private PartenaireRepository partenaireRepository;
	@Autowired
	private ConventionRepository conventionRepository;
	@Autowired
	private ModaliteRepository modaliteRepository;



	@Override
	public Page<Partenaire> getAllPartenaire(String fond ,String modalite , String MontantMinsearchTerm,String MontantMaxsearchTerm, String StatutsearchTerm ,int page, int size) throws ParseException {

			  Pageable pageable = PageRequest.of(page, size);

		        PartenaireSpecification specif = new PartenaireSpecification();
				Specification <Partenaire> spec = specif.getPartenairewithSpec(fond ,modalite ,MontantMinsearchTerm, MontantMaxsearchTerm, StatutsearchTerm);
		Page<Partenaire> pats =  partenaireRepository.findAll(spec,pageable);
		for(Partenaire p : pats.getContent()) {
			p.getConventions().size();
		}
		return pats;


	}

	@Override
	public Long addPartenaire(Partenaire partenaire) {
		// TODO Auto-generated method stub
		return partenaireRepository.save(partenaire).getIdPartenaire();
	}

	@Override
	public void deletePartenaire(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long modifyPartenaire(Partenaire partenaire) {
		return partenaireRepository.save(partenaire).getIdPartenaire();

	}

	@Override
	public List<Modalite> getAllModaliteOfpartenaire(Long partenaireId) {
		Partenaire partenaire = partenaireRepository.findById(partenaireId).get();
		List <Modalite> modalites=new ArrayList<Modalite>() ;
		return modalites;
	}

	@Override
	public Page<Partenaire> getPartenaireList(int page, int size) throws ParseException {
		// TODO Auto-generated method stub
		 Pageable pageable = PageRequest.of(page, size);
		return partenaireRepository.findAll(pageable);

	}

	@Override
	public Convention addPartenairewithcvt( List <Convention> conventions) {
		   Convention c = new Convention() ;
		Partenaire p=partenaireRepository.save(conventions.stream().findFirst().get().getPartenaire());
		 for(Convention convention:conventions){
	convention.setPartenaire(p);
	convention.setDateSignature(convention.getDateSignature());
    c =  conventionRepository.save(convention);
		 }
		 return c;
	}

	@Override
	public Partenaire getPartenaireById(Long id) {
		// TODO Auto-generated method stub
		return partenaireRepository.findById(id).get();
	}

	@Override
	public Long modifyPartenairewithcvt(List<Convention> conventions) {
		Partenaire p=partenaireRepository.save(conventions.stream().findFirst().get().getPartenaire());
		 for(Convention convention:conventions){
	convention.setPartenaire(p);
	convention.setDateSignature(convention.getDateSignature());
   conventionRepository.save(convention);
		 }
		return p.getIdPartenaire();
	}

	@Override
	public Long modifyConvnetion(Long conventionId, String critereModalite, String DateBlocage) throws ParseException {
	  	SimpleDateFormat f = new SimpleDateFormat( "E MMM dd yyyy",Locale.ENGLISH);
		  Date date1re = f.parse( DateBlocage);
		  Convention convention = conventionRepository.findById(conventionId).get();
		  Partenaire partenaire = partenaireRepository.findById(convention.getPartenaire().getIdPartenaire()).get();

		  System.out.println(date1re);
		  partenaire.setDateBlocage(date1re);
		/*if(critereModalite.equals("Specifique")) {
			convention.getModalite().setNatureDemande(TypeDemande.GPP);

		}*/
		  partenaireRepository.save(partenaire);
		return convention.getIdConvention();
	}

	@Override
	public List nobrePartenaireParType() {
		return partenaireRepository.NobrePartenaireParType();
	}

	@Override
	public int nombreTotalPartenaire() {
		// TODO Auto-generated method stub
		return partenaireRepository.NombreTotalPartenaire();
	}

	@Override
	public List<Partenaire> getAllPartenaire() {
		// TODO Auto-generated method stub
		return partenaireRepository.findAll();
	}


}
