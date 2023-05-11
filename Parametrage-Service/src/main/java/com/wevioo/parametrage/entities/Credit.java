package com.wevioo.parametrage.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.enums.Choix;
import com.wevioo.parametrage.enums.FormeRomboursement;
import com.wevioo.parametrage.enums.NatureCredit;
import com.wevioo.parametrage.enums.NatureTauxInteret;
import com.wevioo.parametrage.enums.ObjetCredit;
import com.wevioo.parametrage.enums.Periodicite;
import com.wevioo.parametrage.enums.TypeCredit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Credit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCredit;
	private TypeCredit typeCredit ;
	private NatureCredit natureCredit ; 
	private ObjetCredit objetCredit ;
	private Long montantCredit;
	private Long dureeCredit ; 
	private int solde ; 
	private int montantCreditExige ;
	private int montantContributionPaye;
	private String sureteReel ;
	private int marge ; 
	private String tauxInteret;
	private NatureTauxInteret natureTauxInteret ;
    private Date dateEcheance ;
    private Date dateAutorisation ; 
    private Date dateDerniereTombePrincipale ; 
    private Date datePremiereTombePrincipale ; 
    private FormeRomboursement formeRomboursement;
    private Periodicite periodicite ;
    private int numeroTranche ;
    private Date dateDeblocage;
    private Long montantCreditDebloquee;
    private Long capitalSocial ;
    private Long montantCreditAutorise ; 
    private Choix eligibleRITI ;
    private String schemaCofinancementPoolBancaire ;
    private Choix cofinancement ;
    private String ImmobilisationNettesAvantNouvelInvestissement ;
    private Long montantInvestissement ;
    private Choix NouveauPrometeur ;
    private Date dateEntreeProduction ;
    

}
