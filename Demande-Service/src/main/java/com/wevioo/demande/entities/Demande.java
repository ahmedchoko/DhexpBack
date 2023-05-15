package com.wevioo.demande.entities;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.wevioo.demande.enums.NatureCredit;
import com.wevioo.demande.enums.ObjetCredit;
import com.wevioo.demande.enums.TypeCredit;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;

public class Demande {

	    @Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long idDemande;
		private String numeroCompte ; 
		private String numeroPret ; 
		private String codeCentraleRisque ; 
		private String codedouane ; 
		private String numeroRne ; 
		private String referenceDemande;
		private String montantInvestissement;
		private String nouveauPromoteur;
		private String statut;
		private String Utilisateur;
		private Date dateDeclaration;
		@OneToOne()
		private Beneficiaire beneficiare;
		//private Projet projet;
		@OneToOne()
		private Credit credit ; 
		@OneToOne()
        private Partenaire partenaire ;
		@OneToOne()
        private Autorisation autorisation;
		@OneToOne()
        private Fond fond;
		@OneToOne()
        private Modalite modalite ;
	
}
