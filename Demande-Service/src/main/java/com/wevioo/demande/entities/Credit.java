package com.wevioo.demande.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
 @NoArgsConstructor @AllArgsConstructor @Builder
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
    
	@OneToMany(mappedBy="credit",fetch = FetchType.EAGER)
    private Set <Tranche> tranches;

	public Long getIdCredit() {
		return idCredit;
	}

	public void setIdCredit(Long idCredit) {
		this.idCredit = idCredit;
	}

	public TypeCredit getTypeCredit() {
		return typeCredit;
	}

	public void setTypeCredit(TypeCredit typeCredit) {
		this.typeCredit = typeCredit;
	}

	public NatureCredit getNatureCredit() {
		return natureCredit;
	}

	public void setNatureCredit(NatureCredit natureCredit) {
		this.natureCredit = natureCredit;
	}

	public ObjetCredit getObjetCredit() {
		return objetCredit;
	}

	public void setObjetCredit(ObjetCredit objetCredit) {
		this.objetCredit = objetCredit;
	}

	public Long getMontantCredit() {
		return montantCredit;
	}

	public void setMontantCredit(Long montantCredit) {
		this.montantCredit = montantCredit;
	}

	public Long getDureeCredit() {
		return dureeCredit;
	}

	public void setDureeCredit(Long dureeCredit) {
		this.dureeCredit = dureeCredit;
	}

	public Integer getSolde() {
		return solde;
	}

	public void setSolde(Integer solde) {
		this.solde = solde;
	}

	public Integer getMontantCreditExige() {
		return montantCreditExige;
	}

	public void setMontantCreditExige(Integer montantCreditExige) {
		this.montantCreditExige = montantCreditExige;
	}

	public Integer getMontantContributionPaye() {
		return montantContributionPaye;
	}

	public void setMontantContributionPaye(Integer montantContributionPaye) {
		this.montantContributionPaye = montantContributionPaye;
	}

	public String getSureteReel() {
		return sureteReel;
	}

	public void setSureteReel(String sureteReel) {
		this.sureteReel = sureteReel;
	}

	public Integer getMarge() {
		return marge;
	}

	public void setMarge(Integer marge) {
		this.marge = marge;
	}

	public Integer getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(Integer tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	public NatureTauxInteret getNatureTauxInteret() {
		return natureTauxInteret;
	}

	public void setNatureTauxInteret(NatureTauxInteret natureTauxInteret) {
		this.natureTauxInteret = natureTauxInteret;
	}

	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public Date getDateAutorisation() {
		return dateAutorisation;
	}

	public void setDateAutorisation(Date dateAutorisation) {
		this.dateAutorisation = dateAutorisation;
	}

	public Date getDateDerniereTombePrincipale() {
		return dateDerniereTombePrincipale;
	}

	public void setDateDerniereTombePrincipale(Date dateDerniereTombePrincipale) {
		this.dateDerniereTombePrincipale = dateDerniereTombePrincipale;
	}

	public Date getDatePremiereTombePrincipale() {
		return datePremiereTombePrincipale;
	}

	public void setDatePremiereTombePrincipale(Date datePremiereTombePrincipale) {
		this.datePremiereTombePrincipale = datePremiereTombePrincipale;
	}

	public FormeRomboursement getFormeRomboursement() {
		return formeRomboursement;
	}

	public void setFormeRomboursement(FormeRomboursement formeRomboursement) {
		this.formeRomboursement = formeRomboursement;
	}

	public Periodicite getPeriodicite() {
		return periodicite;
	}

	public void setPeriodicite(Periodicite periodicite) {
		this.periodicite = periodicite;
	}

	public Date getDateDeblocage() {
		return dateDeblocage;
	}

	public void setDateDeblocage(Date dateDeblocage) {
		this.dateDeblocage = dateDeblocage;
	}

	public Long getMontantCreditDebloquee() {
		return montantCreditDebloquee;
	}

	public void setMontantCreditDebloquee(Long montantCreditDebloquee) {
		this.montantCreditDebloquee = montantCreditDebloquee;
	}

	public Long getCapitalSocial() {
		return capitalSocial;
	}

	public void setCapitalSocial(Long capitalSocial) {
		this.capitalSocial = capitalSocial;
	}

	public Long getMontantCreditAutorise() {
		return montantCreditAutorise;
	}

	public void setMontantCreditAutorise(Long montantCreditAutorise) {
		this.montantCreditAutorise = montantCreditAutorise;
	}

	public Choix getEligibleRITI() {
		return eligibleRITI;
	}

	public void setEligibleRITI(Choix eligibleRITI) {
		this.eligibleRITI = eligibleRITI;
	}

	public String getSchemaCofinancementPoolBancaire() {
		return schemaCofinancementPoolBancaire;
	}

	public void setSchemaCofinancementPoolBancaire(String schemaCofinancementPoolBancaire) {
		this.schemaCofinancementPoolBancaire = schemaCofinancementPoolBancaire;
	}

	public Choix getCofinancement() {
		return cofinancement;
	}

	public void setCofinancement(Choix cofinancement) {
		this.cofinancement = cofinancement;
	}

	public Long getImmobilisationNettesAvantNouvelInvestissement() {
		return immobilisationNettesAvantNouvelInvestissement;
	}

	public void setImmobilisationNettesAvantNouvelInvestissement(Long immobilisationNettesAvantNouvelInvestissement) {
		this.immobilisationNettesAvantNouvelInvestissement = immobilisationNettesAvantNouvelInvestissement;
	}

	public Long getMontantInvestissement() {
		return montantInvestissement;
	}

	public void setMontantInvestissement(Long montantInvestissement) {
		this.montantInvestissement = montantInvestissement;
	}

	public Long getMontantrisque() {
		return montantrisque;
	}

	public void setMontantrisque(Long montantrisque) {
		this.montantrisque = montantrisque;
	}

	public Choix getNouveauPrometeur() {
		return nouveauPrometeur;
	}

	public void setNouveauPrometeur(Choix nouveauPrometeur) {
		this.nouveauPrometeur = nouveauPrometeur;
	}

	public Date getDateEntreeProduction() {
		return dateEntreeProduction;
	}

	public void setDateEntreeProduction(Date dateEntreeProduction) {
		this.dateEntreeProduction = dateEntreeProduction;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public Set<Tranche> getTranches() {
		return tranches;
	}

	public void setTranches(Set<Tranche> tranches) {
		this.tranches = tranches;
	}


    

}
