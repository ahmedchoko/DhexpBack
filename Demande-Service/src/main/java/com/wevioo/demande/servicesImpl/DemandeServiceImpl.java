package com.wevioo.demande.servicesImpl;

import java.util.ArrayList;
import java.util.List;

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
import com.wevioo.demande.entities.Personne;
import com.wevioo.demande.entities.PersonneMorale;
import com.wevioo.demande.entities.PersonnePhysique;
import com.wevioo.demande.entities.Projet;
import com.wevioo.demande.enums.TYPEPERSONNE;
import com.wevioo.demande.enums.TypeCredit;
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
import com.wevioo.parametrage.entities.Quotite;
import com.wevioo.parametrage.entities.StopLoss;
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
	@Override
	public boolean Verifmatriculefiscal(String matricule) {
		// TODO Auto-generated method stub
		return false;
	}

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
								demande.setReferenceDemande(demandePreliminaire.getReferencedossierpartenaire());
								demande.setPartenaire(partenaire);
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
								demande.setPartenaire(partenaire);
								demande.setModalite(mod);
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
		return demandeRepository.findAll(pageable);
	}


	@Override
	public Demande createDemande(Demande demande) {

		Demande nouvDemande = Demande.builder()
				.beneficiare(demande.getBeneficiare())
				.codeCentraleRisque(demande.getCodeCentraleRisque())
				.codedouane(demande.getCodedouane())
				.credit(demande.getCredit())
				.dateDeclaration(demande.getDateDeclaration())
				.referenceDemande(demande.getReferenceDemande())
				.numeroPret(demande.getNumeroPret())
				.numeroRne(demande.getNumeroRne())
				.partenaire(demande.getPartenaire())
				.projet(demande.getProjet())
				.statut(demande.getStatut())
				.numeroCompte(demande.getNumeroCompte())
				.utilisateur(demande.getUtilisateur())
				.nouveauPromoteur(demande.getNouveauPromoteur())
				.pieceJointes(demande.getPieceJointes())
				.modalite(demande.getModalite())
				.build();
		return demandeRepository.save(nouvDemande);
	}

	@Override
	public List<Demande> getDemandes() {
		return demandeRepository.findAll();
	}

}
