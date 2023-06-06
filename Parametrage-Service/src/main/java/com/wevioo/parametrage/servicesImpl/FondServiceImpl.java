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
	 * Avoir la liste des fonds.
	 *
	 * @param date1             Date de début de recherche.
	 * @param date2             Date de fin de recherche.
	 * @param StatutsearchTerm  Terme de recherche pour le statut des fonds.
	 * @param MontantMaxsearchTerm Terme de recherche pour le montant maximum des fonds.
	 * @param MontantMinsearchTerm Terme de recherche pour le montant minimum des fonds.
	 * @param page              Numéro de page.
	 * @param size              Taille de la page.
	 * @return Page des fonds correspondants aux critères de recherche.
	 * @throws ParseException Si une erreur de parsing se produit lors de la recherche par date.
	 */
	@Override
	public Page<Fond> getAllFond(String date1, String date2, String StatutsearchTerm, String MontantMaxsearchTerm,
			String MontantMinsearchTerm, int page, int size) throws ParseException {
		Pageable pageable = PageRequest.of(page, size);
		Specification<Fond> spec = FondSpecification.getSpec(MontantMinsearchTerm, MontantMaxsearchTerm,
				StatutsearchTerm, date1, date2);
		Page<Fond> fonds = fondRepository.findAll(spec, pageable);
		for (Fond fond : fonds.getContent()) {
			fond.getSecteurs().size();
		}
		return fonds;
	}

	/**
	 * Ajouter un nouveau fond.
	 *
	 * @param fond Le fond à ajouter.
	 * @return Le fond ajouté.
	 */
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

	/**
	 * Supprimer un fond par son ID.
	 *
	 * @param id L'ID du fond à supprimer.
	 */
	@Override
	public void deleteFond(Long id) {
		Fond fond = fondRepository.getById(id);
		fond.setStatut(Fondstatut.ARCHIVE);
		fondRepository.save(fond);
	}

	/**
	 * Modifier un fond existant.
	 *
	 * @param fond Le fond à modifier.
	 */
	@Transactional
	@Override
	public void modifyFond(Fond fond) {
		Set<Secteur> secteurs = new HashSet<>();
		for (Secteur secteurInput : fond.getSecteurs()) {
			secteurs.add(secteurRepository.findById(secteurInput.getIdSec()).get());
		}
		fond.setSecteurs(secteurs);
		fondRepository.save(fond);
	}

	/**
	 * Avoir un fond par son ID.
	 *
	 * @param id L'ID du fond.
	 * @return Le fond correspondant à l'ID.
	 */
	@Override
	public Fond getFondById(Long id) {
		Fond fond = fondRepository.findById(id).get();
		fond.getModalites();
		return fond;
	}

	/**
	 * Avoir la liste des fonds paginée.
	 *
	 * @param page Numéro de page.
	 * @param size Taille de la page.
	 * @return Page des fonds.
	 * @throws ParseException Si une erreur de parsing se produit lors de la recherche par date.
	 */
	@Override
	public Page<Fond> getAllFond(int page, int size) throws ParseException {
		Pageable pageable = PageRequest.of(page, size);
		return fondRepository.findAll(pageable);
	}

	/**
	 * Avoir la liste des secteurs.
	 *
	 * @return Liste des secteurs.
	 */
	@Override
	public List<Secteur> getAllSecteur() {
		return secteurRepository.findAll().stream().collect(Collectors.toList());
	}

	/**
	 * Avoir la liste de tous les fonds.
	 *
	 * @return Liste des fonds.
	 */
	@Override
	public List<Fond> listFond() {
		return fondRepository.findAll();
	}

	/**
	 * Avoir la liste des fonds non archivés.
	 *
	 * @return Liste des fonds non archivés.
	 */
	@Override
	public List<Fond> getNonArchivedFonds() {
		return fondRepository.getNonArchivedFonds();
	}

	/**
	 * Avoir la liste des fonds de trésorerie par secteur.
	 *
	 * @return Liste des fonds de trésorerie par secteur.
	 */
	@Override
	public List fondTresorieBySecteur() {
		return fondRepository.FondTresorieBySecteur();
	}

	/**
	 * Avoir le compte des fonds par statut.
	 *
	 * @return Compte des fonds par statut.
	 */
	@Override
	public List fondCountByStatus() {
		return fondRepository.FondCountByStatus();
	}

	/**
	 * Avoir le total des fonds.
	 *
	 * @return Total des fonds.
	 */
	@Override
	public float fondTotal() {
		return fondRepository.FondTotal();
	}

	/**
	 * Avoir la liste des activités.
	 *
	 * @return Liste des activités.
	 */
	@Override
	public List<Activite> listActivites() {
		return activiteRepository.findAll();
	}
}
