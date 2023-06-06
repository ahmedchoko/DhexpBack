package com.wevioo.demande.servicesImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.wevioo.demande.dto.DemandeDto;
import com.wevioo.demande.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.wevioo.demande.dto.DemandePreliminaireDTO;
import com.wevioo.demande.entities.Beneficiaire;
import com.wevioo.demande.entities.Credit;
import com.wevioo.demande.entities.Demande;
import com.wevioo.demande.entities.PersonneMorale;
import com.wevioo.demande.entities.PersonnePhysique;
import com.wevioo.demande.entities.Projet;
import com.wevioo.demande.repository.BeneficiaireRepository;
import com.wevioo.demande.repository.CreditRepository;
import com.wevioo.demande.repository.DemandeRepository;
import com.wevioo.demande.repository.PersonneMoraleRepository;
import com.wevioo.demande.repository.PersonnePhysiqueRepository;
import com.wevioo.demande.repository.ProjetRepository;
import com.wevioo.demande.services.DemandeService;
import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.ParametrageEvent;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.entities.StoplossPartenaire;


@Service
public class DemandeServiceImpl implements DemandeService{


	@Autowired
	public CreditRepository creditRepository ;
	@Autowired
	public DemandeRepository demandeRepository ;
	@Autowired
	public PersonneMoraleRepository personneMoraleRepository;
	@Autowired
	public PersonnePhysiqueRepository personnePhysiqueRepository;
	@Autowired
	public BeneficiaireRepository beneficiaireRepository;
	@Autowired
	public ProjetRepository projetRepository;

	private List<Fond> fonds = new ArrayList();
	private List<Partenaire> partenaires=new ArrayList();
	private List<StoplossPartenaire> stoplosspartenaires = new ArrayList();


	@KafkaListener(topics="topic2",groupId="fond")
	public void consumeMessage(ParametrageEvent event) {
		System.out.println("fff" +event.getFond());
   	    this.fonds =event.getFond() ;
   	    this.partenaires = event.getPartenaire();
   	    this.stoplosspartenaires = event.getStpparteanire();
}
	@Override
	public String VerifCritereEligibilite(DemandePreliminaireDTO demandePreliminaire) {
	    boolean estEligible = true;
	    String message = "";
        Modalite mod = null ;
	    for (Fond fond : this.fonds) {
	        if (fond.getIdFond().toString().equals(demandePreliminaire.getFond())) {
	            if (!fond.getStatut().toString().equals("ACTIF") && estEligible==true) {
	                estEligible = false;
	                message = "Le fond choisi n'est pas actif";
	            }

	            boolean modaliteTrouvee = false;
	            String modalites = "";

	            for (Modalite modalite : fond.getModalites()) {
	                modalites = modalites + " " + modalite.getTypeModalite().name();
	                if (modalite.getTypeModalite().toString().equals(demandePreliminaire.getModalite()) && modaliteTrouvee==false) {
	                	System.out.println("mod est choisi");
	                    modaliteTrouvee = true;
	                    mod= modalite;
	                    if (modalite.getStatut().toString().equals("NONACTIF")) {
	                        estEligible = false;
	                        message = "La modalité choisie n'est pas active";
	                    }
	                 
	                }  
	            }

	            if (!modaliteTrouvee) {
	                estEligible = false;
	                message = "La modalité choisie n'est pas affectée au fond " + fond.getNomCompletFond() +
	                          ". Vous pouvez choisir l'une des modalités suivantes : " + modalites;
	            }

	            break; // Sortir de la boucle une fois le fond correspondant trouvé
	        }
	    }

	    if (estEligible) {
	        for (Partenaire partenaire : this.partenaires) {
	            if (demandePreliminaire.getNomCompletPartenaire().equals(partenaire.getNomCompletPartenaire())) {
	                if (!partenaire.getStatut().toString().equals("ACTIF") && estEligible==true ) {
	                    estEligible = false;
	                    message = "Le partenaire choisi n'est pas actif";

	                }

	                boolean conventionTrouvee = false;
	                String conventions = "";

	                for (Convention convention : partenaire.getConventions()) {
	                    conventions = conventions + " " + convention.getModalite().getTypeModalite();
	                    if (convention.getModalite().getTypeModalite().toString().equals(demandePreliminaire.getModalite())) {
	                        conventionTrouvee = true;
	                        Long res=(long) 0;
							Long montant = demandePreliminaire.getMontantinvestissement();
							for(StoplossPartenaire stoplosspartenaire : stoplosspartenaires) {
								if(partenaire.getIdPartenaire().equals(stoplosspartenaire.getPartenaire().getIdPartenaire())) {
										Integer taux = stoplosspartenaire.getTauxSLPartenaire() ;
							 res =	(stoplosspartenaire.getStoploss().getFond().getTresorerieFond() * taux) /100;
								}

							}
							if(montant< res) {
								message = "Parfait demande preliminaire est accepte";
								Demande demande = new Demande();
								demande.setStatut("ENCOURS");
								demande.setReferenceDemande(demandePreliminaire.getReferencedossierpartenaire());
								demande.setPartenaire(partenaire.getIdPartenaire());
								demande.setNouveauPromoteur(demandePreliminaire.getNouvPromo());
								demande.setNumeroCompte(demandePreliminaire.getNumerocompte());
								demande.setNumeroRne(demandePreliminaire.getNumerorne());
								Credit credit =new Credit();
							    credit.setTypeCredit(demandePreliminaire.getTypecredit());
							    credit.setMontantCreditAutorise(demandePreliminaire.getMontantcreditautorise());
							    credit.setObjetCredit(demandePreliminaire.getObjetcredit());
							    Credit creditsaved = creditRepository.save(credit);
								demande.setCredit(creditsaved);
								Projet projet = new Projet();
								projet.setTypeProjet(demandePreliminaire.getTypeprojet());
								Projet projetsaved = projetRepository.save(projet);
								demande.setPartenaire(partenaire.getIdPartenaire());
								demande.setModalite(mod.getIdModalite());
								demande.setProjet(projetsaved);
								Beneficiaire beneficiare = new Beneficiaire();
								beneficiare.setTypPersonne(demandePreliminaire.getTypebenificiaire());
								if(demandePreliminaire.getTypebenificiaire().equals(TYPEPERSONNE.MORALE)) {
									Beneficiaire beneficiaresaved = beneficiaireRepository.save(beneficiare);
									PersonneMorale personnemorale = new PersonneMorale();
									personnemorale.setBeneficiaire(beneficiaresaved);
									PersonneMorale personnemoralesaved = personneMoraleRepository.save(personnemorale);

									demande.setBeneficiare(beneficiaresaved);

								}
								else {
									Beneficiaire beneficiaresaved = beneficiaireRepository.save(beneficiare);
									PersonnePhysique personnephysique = new PersonnePhysique();
									personnephysique.setBeneficiaire(beneficiaresaved);
									PersonnePhysique personnephysiquesaved = personnePhysiqueRepository.save(personnephysique);
									demande.setBeneficiare(beneficiaresaved);
								}
								demandeRepository.save(demande);
					
							}
							else {
								message = "Le montant mis dépasse le taux du partenaire choisis sur ce fond" ;

							}
	                    }
	                }

	                if (!conventionTrouvee && estEligible==true) {
	                    estEligible = false;
	                    message = "Le partenaire choisi n'a pas signé un contrat " + demandePreliminaire.getModalite() +
	                              ". Vous pouvez choisir l'une des modalités suivantes signées par ce partenaire : " + conventions;

	                }
	            }
	        }
	    }

	    return message;
	}

	@Override
	public Page <Demande> getDemandePreliminaire(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return demandeRepository.getDemandesEncours(pageable);
	}



	@Override
	public Demande updateDemande(DemandeDto demande) throws ParseException {
	Demande updatedDemande = demandeRepository.findById(Long.valueOf(demande.getIdDemande()))
			.orElseThrow(() -> new NoSuchElementException("Resource with id "+" not found"));

		/////Beneficiaire
		updatedDemande.getBeneficiare().setAdresse(demande.getAddresse());
		updatedDemande.getBeneficiare().setNatureActivite(demande.getNatureActivite());
		//updatedDemande.getBeneficiare().setCodeActivite(demande.getActivites().getIdAct());
		updatedDemande.getBeneficiare().setCodePostal(Integer.getInteger(demande.getCodePostal()));
		updatedDemande.getBeneficiare().setDelegation(demande.getDelegation());
		updatedDemande.getBeneficiare().setRegion(demande.getRegion());
		updatedDemande.getBeneficiare().setActivite(demande.getActivites().getCodeActivite());
		updatedDemande.getBeneficiare().setNumeroRib(demande.getNumerocompte());
		if (demande.getBeneficiaire().equals(TYPEPERSONNE.PHYSIQUE)) {
			updatedDemande.getBeneficiare().getPersonnePhysique().setNumPieceIdentification(demande.getNumPieceIdentification());
			updatedDemande.getBeneficiare().getPersonnePhysique().setTelephonefixe(demande.getTelephonefixe());
			updatedDemande.getBeneficiare().getPersonnePhysique().setTelephoneMobile1(demande.getTelephoneMobile1());
			updatedDemande.getBeneficiare().getPersonnePhysique().setTelephoneMobile2(demande.getTelephoneMobile2());
			updatedDemande.getBeneficiare().getPersonnePhysique().setGenre(demande.getGenre());
			updatedDemande.getBeneficiare().getPersonnePhysique().setMail(demande.getMail());
			updatedDemande.getBeneficiare().getPersonnePhysique().setNomCompletBenificiaire(demande.getNomCompletBenificiare());
			updatedDemande.getBeneficiare().getPersonnePhysique().setTypePieceIdentification(PieceIdentification.valueOf(demande.getTypePieceIdentification()));
		}
		if( demande.getBeneficiaire().equals(TYPEPERSONNE.MORALE) ) {
			updatedDemande.getBeneficiare().getPersonneMorale().setFormeJuridique(FormeJuridique.valueOf(demande.getFormeJuridique()));
			updatedDemande.getBeneficiare().getPersonneMorale().setRaisonSociale(demande.getRaisonSociale());
		}

		//////Credit
		//updatedDemande.getCredit().getCapitalSocial(demande);
		updatedDemande.getCredit().setCofinancement(Choix.valueOf(demande.getCofinancement()));
		SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
		Date DateBlocage = f.parse(demande.getDateDeblocage());
		updatedDemande.getCredit().setDateDeblocage(DateBlocage);
		Date DateDerniereTombeePrincipale = f.parse(demande.getDateDerniereTombeePrincipale());
		updatedDemande.getCredit().setDateDerniereTombePrincipale(DateDerniereTombeePrincipale);
		Date DateEntreeProduction = f.parse(demande.getDateEntreeProduction());
		updatedDemande.getCredit().setDateEntreeProduction(DateEntreeProduction);
		Date DatePremiereTombeePrincipale = f.parse(demande.getDatePremiereTombeePrincipale());
		updatedDemande.getCredit().setDatePremiereTombePrincipale(DatePremiereTombeePrincipale);
		Date DateAutorisation = f.parse(demande.getDateDeclaration());
		updatedDemande.getCredit().setDateAutorisation(DateAutorisation);
		updatedDemande.getCredit().setDureeCredit(Long.valueOf(demande.getDureeCredit()));
		updatedDemande.getCredit().setEligibleRITI(Choix.valueOf(demande.getRitic()));
		updatedDemande.getCredit().setFormeRomboursement(FormeRomboursement.valueOf(demande.getFormeRomboursement()));
		updatedDemande.getCredit().setPeriodicite(Periodicite.valueOf(demande.getPeriodicite()));
		//updatedDemande.getCredit().setImmobilisationNettesAvantNouvelInvestissement();
		updatedDemande.getCredit().setMontantCredit(Long.valueOf(demande.getMontantCredit()));
		updatedDemande.getCredit().setMontantrisque(Long.valueOf(demande.getMontantRisque()));
		updatedDemande.getCredit().setMontantCreditDebloquee(Long.valueOf(demande.getMontantGarantieDebloque()));


		/////Projet
		updatedDemande.getProjet().setDelegation(demande.getDelegationProjet().getDelegation());
		updatedDemande.getProjet().setCodePostal(Integer.getInteger(demande.getCodePostal()));
		updatedDemande.getProjet().setSite(demande.getZoneImplementation());
		updatedDemande.getProjet().setCodePostal(demande.getDelegationProjet().getCodePostal());


		////Demande
		updatedDemande.setNouveauPromoteur(demande.getNouveauPromoteur());
		updatedDemande.setReferenceDemande(demande.getReferenceDemande());
		updatedDemande.setUtilisateur(demande.getUtilisateur());
		updatedDemande.setCodeCentraleRisque(demande.getCodecentralerisques());
		Date DateDeclaration = f.parse(demande.getDateDeclaration());
		updatedDemande.setDateDeclaration(DateDeclaration);
		updatedDemande.setStatut(demande.getStatut());

		demandeRepository.save(updatedDemande);
		return updatedDemande;
	}


	@Override
	public Page<Demande> getDemandes(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("idDemande"));
		Page<Demande> myDataPage = demandeRepository.getDemandesValidees(pageable);
		return myDataPage;
	}

}
