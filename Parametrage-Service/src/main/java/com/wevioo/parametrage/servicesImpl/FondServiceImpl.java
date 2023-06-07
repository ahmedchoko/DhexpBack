package com.wevioo.parametrage.servicesImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.wevioo.parametrage.common.NotFoundException;
import com.wevioo.parametrage.entities.Activite;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Secteur;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.repository.ActiviteRepository;
import com.wevioo.parametrage.repository.FondRepository;
import com.wevioo.parametrage.repository.SecteurRepository;
import com.wevioo.parametrage.services.FondService;
import com.wevioo.parametrage.specification.FondSpecification;


/**
 * Implémentation du service pour la gestion des fonds.
 */
@Service
public class FondServiceImpl implements FondService {

	@Autowired
	private FondRepository fondRepository;

	@Autowired
	private SecteurRepository secteurRepository;

	@Autowired
	private ActiviteRepository activiteRepository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Fond> getAllFond(String date1, String date2, String StatutsearchTerm, String MontantMaxsearchTerm,
	                            String MontantMinsearchTerm, int page, int size) throws ParseException {
	    try {
	        Pageable pageable = PageRequest.of(page, size);
	        Specification<Fond> spec = FondSpecification.getSpec(MontantMinsearchTerm, MontantMaxsearchTerm,
	                StatutsearchTerm, date1, date2);
	        Page<Fond> fonds = fondRepository.findAll(spec, pageable);
	        for (Fond fond : fonds.getContent()) {
	            fond.getSecteurs().size();
	        }
	        return fonds;
	    } catch (Exception e) {
	        // Handle the exception
	        e.printStackTrace();
	        return Page.empty();
	    }
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public Fond addFond(Fond fond) {
	    try {
	        validateFond(fond); // Validate non-nullable attributes
	        
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
	    } catch (DataIntegrityViolationException ex) {
	        // Handle the exception for data integrity violations
	        throw new IllegalArgumentException("Validation error: " + ex.getMessage());
	    } catch (Exception ex) {
	        // Handle other exceptions
	        throw new IllegalStateException("An error occurred while adding the Fond: " + ex.getMessage());
	    }
	}
	
	private void validateFond(Fond fond) {
	    if (fond.getMontantMax() == null) {
	        throw new IllegalArgumentException("Validation error: MontantMax is required");
	    }

	    if (fond.getMontantMin() == null) {
	        throw new IllegalArgumentException("Validation error: MontantMin is required");
	    }

	    if (fond.getDateClotureFond() == null) {
	        throw new IllegalArgumentException("Validation error: DateClotureFond is required");
	    }

	    if (fond.getDateDemarrageFond() == null) {
	        throw new IllegalArgumentException("Validation error: DateDemarrageFond is required");
	    }

	    if (fond.getStatut() == null) {
	        throw new IllegalArgumentException("Validation error: Statut is required");
	    }

	    if (fond.getAbrevFond() == null) {
	        throw new IllegalArgumentException("Validation error: AbrevFond is required");
	    }

	    if (fond.getNomArabeFond() == null) {
	        throw new IllegalArgumentException("Validation error: NomArabeFond is required");
	    }

	    if (fond.getNomCompletFond() == null) {
	        throw new IllegalArgumentException("Validation error: NomCompletFond is required");
	    }

	    if (fond.getNomFond() == null) {
	        throw new IllegalArgumentException("Validation error: NomFond is required");
	    }

	    if (fond.getTresorerieFond() == null) {
	        throw new IllegalArgumentException("Validation error: TresorerieFond is required");
	    }
	    
	    // Check uniqueness of attributes
        Fond existingAbrevFond = fondRepository.findByAbrevFondExceptId(fond.getAbrevFond(), fond.getIdFond());
        if (existingAbrevFond != null) {
            throw new IllegalArgumentException("Abreviation must be unique.");
        }
        // Check uniqueness of attributes
        Fond existingNomFond = fondRepository.findByNomFondExceptId(fond.getNomFond(), fond.getIdFond());
        if (existingNomFond != null) {
            throw new IllegalArgumentException("NomFond must be unique.");
        }
        
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteFond(Long id) {
	    try {
	        Fond fond = fondRepository.getById(id);
	        fond.setStatut(Fondstatut.ARCHIVE);
	        fondRepository.save(fond);
	    } catch (EntityNotFoundException e) {
	        // Handle the case where the Fond entity with the given id is not found
	        throw new NotFoundException("Fond not found with id: " + id);
	    } catch (Exception e) {
	        // Handle any other unexpected exception that may occur during the deletion process
	        throw new ServiceException("Failed to delete Fond with id: " + id, e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public void modifyFond(Fond fond) {
	    try {
	    	validateFond(fond);
	        Set<Secteur> secteurs = new HashSet<>();
	        for (Secteur secteurInput : fond.getSecteurs()) {
	            Secteur existingSecteur = secteurRepository.findById(secteurInput.getIdSec()).orElse(null);
	            if (existingSecteur != null) {
	                secteurs.add(existingSecteur);
	            } else {
	                throw new NotFoundException("Secteur not found with id: " + secteurInput.getIdSec());
	            }
	        }
	        fond.setSecteurs(secteurs);
	        fondRepository.save(fond);
	    } catch (Exception e) {
	        // Handle the exception
	        throw new ServiceException("Error modifying Fond: " + e.getMessage(), e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Fond getFondById(Long id) {
	    try {
	        Fond fond = fondRepository.findById(id).orElse(null);
	        if (fond != null) {
	            fond.getModalites();
	        } else {
	            throw new NotFoundException("Fond not found with id: " + id);
	        }
	        return fond;
	    } catch (Exception e) {
	        // Handle the exception
	        throw new ServiceException("Error retrieving Fond: " + e.getMessage(), e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Fond> getAllFond(int page, int size) throws ParseException {
	    try {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Fond> fondPage = fondRepository.findAll(pageable);
	        if (fondPage.isEmpty()) {
	            // Handle the case when the page is empty
	            return Page.empty();
	        }
	        return fondPage;
	    } catch (Exception e) {
	        // Handle the exception

	        throw new ServiceException("Error retrieving all Fond: " + e.getMessage(), e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Secteur> getAllSecteur() {
	    try {
	        return secteurRepository.findAll().stream().collect(Collectors.toList());
	    } catch (Exception e) {
	        // Handle the exception
	        throw new ServiceException("Error retrieving all Secteur: " + e.getMessage(), e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Fond> listFond() {
	    try {
	        return fondRepository.findAll();
	    } catch (Exception e) {
	        // Handle the exception
	        throw new ServiceException("Error retrieving Fond list: " + e.getMessage(), e);
	    }
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Fond> getNonArchivedFonds() {
	    try {
	        return fondRepository.getNonArchivedFonds();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List fondTresorieBySecteur() {
	    try {
	        return fondRepository.FondTresorieBySecteur();
	    } catch (Exception e) {
	        // Handle the exception
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List fondCountByStatus() {
	    try {
	        return fondRepository.FondCountByStatus();
	    } catch (Exception e) {
	        // Handle the exception 
	        e.printStackTrace();
	        return Collections.emptyList();
	    }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float fondTotal() {
	    try {
	        return fondRepository.FondTotal();
	    } catch (Exception e) {
	        // Handle the exception
	        e.printStackTrace();
	        return 0.0f; // Return a default value (0.0f in this case)
	    }
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Activite> listActivites() {
	    try {
	        return activiteRepository.findAll();
	    } catch (Exception e) {
	        // Handle the exception here
	        e.printStackTrace();
	        return new ArrayList<>();
	    }
	}

}
