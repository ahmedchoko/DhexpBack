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
/*
	@KafkaListener(topics="topic2",groupId="fond")
	public void consumeMessage(ParametrageEvent event) {
		System.out.println("fff" +event.getFond());
   	    this.fonds =event.getFond() ;
   	    this.partenaires = event.getPartenaire();
   	    this.stoplosspartenaires = event.getStpparteanire();
}*/
	@Override
	public String VerifCritereEligibilite(DemandePreliminaireDTO demandePreliminaire) {
		System.out.println("fonds "+this.stoplosspartenaires);
	System.out.println("fonds "+this.fonds);
	String message ="";
	String modalites = "";
	String conventions = "";
	System.out.println("demandePreliminaire "+demandePreliminaire);
	for(Fond fond : this.fonds) {
		if(fond.getIdFond().toString().equals(demandePreliminaire.getFond()) &&(fond.getStatut().toString().equals("ACTIF"))) {
			System.out.println(fond.getModalites());
			for(Modalite modalite : fond.getModalites()) {
				modalites=modalites+" "+modalite.getTypeModalite().name();
				if(modalite.getTypeModalite().toString().equals(demandePreliminaire.getModalite())) {
					if(modalite.getStatut().toString().equals("NONACTIF") ) {
						message = "modalite choisie n'est pas ACTIF";
						return message;
					}
					else {
						for(Partenaire partenaire : this.partenaires) {
								if(demandePreliminaire.getNomCompletPartenaire().equals(partenaire.getNomCompletPartenaire())) {
									 if (partenaire.getStatut().toString().equals("ACTIF")) {
										 	for(Convention convention : partenaire.getConventions()) {
										 		conventions = conventions+""+convention.getModalite().getTypeModalite();
										 		
								if(convention.getModalite().getTypeModalite().toString().equals(demandePreliminaire.getModalite())) {
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
										demande.setCodeCentraleRisque(demandePreliminaire.getCodecentralerisques());
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
										demande.setModalite(modalite);
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
										return message;
									}
									else {
										message = "Le montant mis dépasse le taux du partenaire choisis sur ce fond" ;
										return message;
									}
							
								}
								else {
									message = "Partenaire choisi n'a pas signé un Contrat "+ demandePreliminaire.getModalite() +"tu peux choisir l'un de ces modalités signés par ce partenaire "+conventions ;
									return message;
								}
							}
									 }
									 else {
									message = "Parteanire choisie n'est pas ACTIF";
									return message;
								}
						
						}
						}
					
					}
				}
				else {
					
					message = "modalite choisie " + demandePreliminaire.getModalite()+" n'est pas affecté au fond "+fond.getNomCompletFond() +" tu peux choisir l'un de ces modalites "+modalites;
				}
			}
		}
		else {
			message="Fond choisi n'est pas actif";
			return message;
		}
	}
	return message;
	}

	@Override
	public Page <Demande> getDemandePreliminaire(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return demandeRepository.findAll(pageable);
	}

}
