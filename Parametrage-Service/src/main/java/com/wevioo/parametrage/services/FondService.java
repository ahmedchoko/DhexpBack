package com.wevioo.parametrage.services;

import java.text.ParseException;

import java.util.List;


import org.springframework.data.domain.Page;

import com.wevioo.parametrage.entities.Activite;
import com.wevioo.parametrage.entities.Fond;

import com.wevioo.parametrage.entities.Secteur;

public interface FondService {
	
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
	public Page <Fond> getAllFond(String date1 , String date2 ,String StatutsearchTerm,String MontantMaxsearchTerm,String MontantMinsearchTerm, int page, int size) throws ParseException;
	
	/**
	 * Avoir la liste des fonds paginée.
	 *
	 * @param page Numéro de page.
	 * @param size Taille de la page.
	 * @return Page des fonds.
	 * @throws ParseException Si une erreur de parsing se produit lors de la recherche par date.
	 */
	
	public Page <Fond> getAllFond(int page, int size) throws ParseException;
	
	/**
	 * Ajouter un nouveau fond.
	 *
	 * @param fond Le fond à ajouter.
	 * @return Le fond ajouté.
	 */
	public Fond addFond(Fond fond );
	/**
	 * Supprimer un fond par son ID.
	 *
	 * @param id L'ID du fond à supprimer.
	 */
	
	public void deleteFond(Long id);

	/**
	 * Modifier un fond existant.
	 *
	 * @param fond Le fond à modifier.
	 */
	public void modifyFond(Fond fond);
	/**
	 * Avoir un fond par son ID.
	 *
	 * @param id L'ID du fond.
	 * @return Le fond correspondant à l'ID.
	 */
	public Fond getFondById(Long id);
	/**
	 * Avoir la liste des secteurs.
	 *
	 * @return Liste des secteurs.
	 */
	public List<Secteur> getAllSecteur ();
	/**
	 * Avoir la liste de tous les fonds.
	 *
	 * @return Liste des fonds.
	 */
	
	public List<Fond> listFond();
	/**
	 * Avoir la liste des fonds non archivés.
	 *
	 * @return Liste des fonds non archivés.
	 */
	
	public List<Fond> getNonArchivedFonds();
	/**
	 * Avoir la liste des fonds de trésorerie par secteur.
	 *
	 * @return Liste des fonds de trésorerie par secteur.
	 */
    public List fondTresorieBySecteur();
	/**
	 * Avoir le compte des fonds par statut.
	 *
	 * @return Compte des fonds par statut.
	 */
    public List fondCountByStatus();
    /**
	 * Avoir la liste des activités.
	 *
	 * @return Liste des activités.
	 */
    public List<Activite> listActivites();

	/**
	 * Avoir le total des fonds.
	 *
	 * @return Total des fonds.
	 */
	
    public float fondTotal();
}

