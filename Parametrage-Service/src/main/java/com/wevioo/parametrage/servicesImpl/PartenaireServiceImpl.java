package com.wevioo.parametrage.servicesImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
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
		return partenaireRepository.findAll(spec,pageable);
	}

	@Override
	public void addPartenaire(Partenaire partenaire) {
		// TODO Auto-generated method stub
		partenaireRepository.save(partenaire);
	}

	@Override
	public void deletePartenaire(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyPartenaire(Partenaire partenaire) {
		partenaireRepository.save(partenaire);
		
	}

	@Override
	public List<Modalite> getAllModaliteOfpartenaire(Long partenaireId) {
		Partenaire partenaire = partenaireRepository.findById(partenaireId).get();
		List <Modalite> modalites=new ArrayList<Modalite>() ;
		 for(Convention convention:partenaire.getConventions()){
			// modalites.add(convention.getModalite());
		    }
		return modalites;
	}

	@Override
	public Page<Partenaire> getPartenaireList(int page, int size) throws ParseException {
		// TODO Auto-generated method stub
		 Pageable pageable = PageRequest.of(page, size);
		return partenaireRepository.findAll(pageable);

	}

	@Override
	public void addPartenairewithcvt( List <Convention> conventions) {
		Partenaire p=partenaireRepository.save(conventions.stream().findFirst().get().getPartenaire());
		 for(Convention convention:conventions){
	convention.setPartenaire(p);
	convention.setDateSignature(convention.getDateSignature());
    conventionRepository.save(convention);
		 }
	}

	@Override
	public Partenaire getPartenaireById(Long id) {
		// TODO Auto-generated method stub
		return partenaireRepository.findById(id).get();
	}

	@Override
	public void modifyPartenairewithcvt(List<Convention> conventions) {
		Partenaire p=partenaireRepository.save(conventions.stream().findFirst().get().getPartenaire());
		 for(Convention convention:conventions){
	convention.setPartenaire(p);
	convention.setDateSignature(convention.getDateSignature());
   conventionRepository.save(convention);
		 }
		
	}
	

}
