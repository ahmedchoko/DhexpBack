package com.wevioo.demande.entities;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.demande.enums.Choix;
import com.wevioo.demande.enums.FormeRomboursement;
import com.wevioo.demande.enums.NatureCredit;
import com.wevioo.demande.enums.NatureTauxInteret;
import com.wevioo.demande.enums.ObjetCredit;
import com.wevioo.demande.enums.Periodicite;
import com.wevioo.demande.enums.TypeCredit;

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
    @Enumerated(EnumType.STRING)
	private TypeCredit typeCredit ;
    @Enumerated(EnumType.STRING)
	private NatureCredit natureCredit ;
    @Enumerated(EnumType.STRING)
	private ObjetCredit objetCredit ;
	private Long montantCredit;
	private Long dureeCredit ; 
	private Integer solde ;
	private Integer montantCreditExige ;
	private Integer montantContributionPaye;
	private String sureteReel ;
	private Integer marge ;
	private Integer tauxInteret;
    @Enumerated(EnumType.STRING)
	private NatureTauxInteret natureTauxInteret ;
    private Date dateEcheance ;
    private Date dateAutorisation ; 
    private Date dateDerniereTombePrincipale ; 
    private Date datePremiereTombePrincipale ;
    @Enumerated(EnumType.STRING)
    private FormeRomboursement formeRomboursement;
    @Enumerated(EnumType.STRING)
    private Periodicite periodicite ;
    private Date dateDeblocage;
    private Long montantCreditDebloquee;
    private Long capitalSocial ;
    private Long montantCreditAutorise ;
    @Enumerated(EnumType.STRING)
    private Choix eligibleRITI ;
    private String schemaCofinancementPoolBancaire ;
    @Enumerated(EnumType.STRING)
    private Choix cofinancement ;
    private Long immobilisationNettesAvantNouvelInvestissement ;
    private Long montantInvestissement ;
    private Long montantrisque ;
    @Enumerated(EnumType.STRING)
    private Choix nouveauPrometeur ;
    private Date dateEntreeProduction ;
    @JsonIgnore
	@OneToOne(mappedBy="credit")
    private Demande demande;
    
	@OneToOne()
    private Tranche tranche;
    

}
