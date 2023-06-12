package com.wevioo.parametrage.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.repository.ConventionRepository;
import com.wevioo.parametrage.repository.FondRepository;
import com.wevioo.parametrage.repository.ModaliteRepository;
import com.wevioo.parametrage.repository.PartenaireRepository;
import com.wevioo.parametrage.servicesimpl.FondServiceImpl;
import com.wevioo.parametrage.servicesimpl.ModaliteServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartenaireServiceTest {

	

	 private  FondServiceImpl fondService ;
	 private  FondRepository fondRepository;
	@Mock	 
	private  ModaliteRepository modaliteRepository;
	private PartenaireRepository partenairerepository;
	private ConventionRepository Conventionrepository;
	private PartenaireService partenaireservice ;
	@InjectMocks
    private ModaliteServiceImpl modaliteservice ;
	
	@Test
	public void testAddPartenairewithconvention() throws ParseException {
		Modalite m = new Modalite((long)2,"ASN8");
		Mockito.when(modaliteRepository.findById((long)2)).thenReturn(Optional.of(m));
		Partenaire partenaire = new Partenaire("ABC");
		Convention convention = new Convention(partenaire);
		convention.setModalite(modaliteservice.getModaliteById((long)2));
		ArrayList<Convention> cnvs = new ArrayList();
		cnvs.add(convention);
		//Convention convnetion = partenaireservice.addPartenairewithcvt(cnvs);
	//	assertTrue(partenairerepository.findById(convnetion.getPartenaire().getIdPartenaire()).isPresent());
		//assertTrue(Conventionrepository.findById(convnetion.getIdConvention()).isPresent());
	//	Conventionrepository.deleteById(convnetion.getIdConvention());
	//	partenairerepository.deleteById(convnetion.getPartenaire().getIdPartenaire());
	}
	@Test
	public void testgetPartenaireById() throws ParseException {
		Partenaire partenaire = new Partenaire("ABC");
		Partenaire partenairesaved = partenairerepository.save(partenaire);
		assertNotNull(partenaireservice.getPartenaireById(partenairesaved.getIdPartenaire()));
		partenairerepository.deleteById(partenairesaved.getIdPartenaire());
	}
	@Test
	public void testgetAllPartenaire() throws ParseException {
		int pageSize = 5; // the size of each page
	    int pageToRetrieve = 1; // the index of the last page (0-based)

	    // Retrieve the last page of Partenaire entities
	    Pageable pageable = PageRequest.of(pageToRetrieve, pageSize, Sort.by("idPartenaire").descending());
	    Page<Partenaire> lastPageOfPartenairesBefore = partenaireservice.getPartenaireList(pageable.getPageNumber(),pageable.getPageSize());

	    // Add a new Partenaire entity
	    Partenaire partenaire = new Partenaire("ABC");
	    Partenaire savedPartenaire = partenairerepository.save(partenaire);

	    // Retrieve the last page of Partenaire entities again
	    Page<Partenaire> lastPageOfPartenairesAfter = partenaireservice.getPartenaireList(pageable.getPageNumber(),pageable.getPageSize()+1);

	    // Check that the size of the last page increased by 1 after adding the new entity
	    assertEquals(lastPageOfPartenairesBefore.getSize()+1, lastPageOfPartenairesAfter.getSize());

	    // Clean up: delete the added entity
	    partenairerepository.deleteById(savedPartenaire.getIdPartenaire());
	}
}
