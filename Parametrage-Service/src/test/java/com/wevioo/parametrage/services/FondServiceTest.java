package com.wevioo.parametrage.services;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.wevioo.parametrage.ParametrageApplication;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.repository.FondRepository;
import com.wevioo.parametrage.servicesImpl.FondServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FondServiceTest {
	
	@Autowired
	 private  FondServiceImpl fondService ;
	@Autowired
	 private  FondRepository fondRepository;
	

	@Test
	public void testAddFond() throws ParseException {
		Fond fond = new Fond("ABCD","A145S","ABCD",(long)150);
		Fond fondsaved = fondService.addFond(fond);
		assertNotNull(fondsaved.getIdFond());
		fondRepository.deleteById(fondsaved.getIdFond());
	}
	@Test
	public void testRetrieveAllFonds() throws ParseException {
		List<Fond> fonds = fondService.listFond();
		int expected = fonds.size();
		Fond fond = new Fond("ABCD","A145S","ABCD",(long)150);
		Fond fondsaved = fondService.addFond(fond);
		assertEquals(expected + 1, fondService.listFond().size());
		fondRepository.deleteById(fondsaved.getIdFond());

	}

	@Test
	public void testDeleteFond() throws ParseException {
		Fond fond = new Fond("ABCD","A145S","ABCD",(long)150);
		Fond fondsaved = fondService.addFond(fond);
		fondRepository.delete(fondsaved);
		assertFalse(fondRepository.findById(fondsaved.getIdFond()).isPresent());

	}
}