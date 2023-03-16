package com.wevioo.parametrage.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.repository.ModaliteRepository;
import com.wevioo.parametrage.repository.PartenaireRepository;
import com.wevioo.parametrage.services.PartenaireService;


@Service
public class PartenaireServiceImpl implements PartenaireService{

	
	@Autowired
	private PartenaireRepository partenaireRepository;
	
	@Autowired
	private ModaliteRepository modaliteRepository;
	
	@Override
	public Page<Partenaire> getAllPartenaire(String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
		return this.partenaireRepository.findByFirstNameLike(search , pageable);
	}

	@Override
	public void addPartenaire(Partenaire fond) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePartenaire(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyPartenaire(Partenaire fond) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Modalite> getAllModaliteOfpartenaire(Long partenaireId) {
		Partenaire partenaire = partenaireRepository.findById(partenaireId).get();
		List <Modalite> modalites=new ArrayList<Modalite>() ;
		 for(Convention convention:partenaire.getConventions()){
			 modalites.add(convention.getModalite());
		    }
		return modalites;
	}
	

}
