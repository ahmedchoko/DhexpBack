package com.wevioo.parametrage.servicesImpl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.repository.ConventionRepository;
import com.wevioo.parametrage.repository.FondRepository;
import com.wevioo.parametrage.repository.PartenaireRepository;
import com.wevioo.parametrage.services.FondService;

@Service
public class FondServiceImpl implements FondService{

	@Autowired
	private FondRepository fondRepository;
	@Autowired
	private PartenaireRepository partenaireRepository;
	@Autowired
	private ConventionRepository conventionRepository;
	@Override
	public Page <Fond> getAllFond(String search, int page, int size) {
		  // create Pageable object using the page and size
        Pageable pageable = PageRequest.of(page, size);
        // fetch the page object by additionally passing pageable with the filters
		return fondRepository.findByFirstNameLike(search,pageable);
	}

	@Override
	public void addFond(Fond fond) {
		fondRepository.save(fond);
		
	}

	@Override
	public void deleteFond(Long id) {
	fondRepository.deleteById(id);
		
	}

	@Override
	public void modifyFond(Fond fond) {
		fondRepository.save(fond);
	}

	@Override
	public List<Fond> getAllFondofPartenaire(Long partenaireId) {
		Partenaire partenaire =this.partenaireRepository.findById(partenaireId).get();
		List <Convention> conventions = this.conventionRepository.findAll();
		List <Fond> fonds=new ArrayList<Fond>() ;
		List <Modalite> mods=new ArrayList<Modalite>() ;
		List < Object> test1=new ArrayList() ;
	    Map  < Fond, Modalite > test = new HashMap <  Fond,Modalite> ();
	    Map  < Fond, List <Modalite> > res = new HashMap <  Fond,List <Modalite>> ();
		 for(Convention convention:conventions){
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
		 for (Map.Entry<Fond, Modalite > m : test.entrySet()) {
			 
		 for (Map.Entry<Fond, Modalite > n : test.entrySet()) {
		if(m.getKey().getIdFond()==n.getKey().getIdFond()) {
			
			mods.add(m.getValue()); 
			res.put(m.getKey(),mods);
		}
		
		 }
		 
		 }
		
		 System.out.println(res);
		 return fonds;
	}
	  @Override
	    public Fond getFondById(Long id) {
	        Fond fond = fondRepository.findById(id).get();
	        return fond;
	    }
}
