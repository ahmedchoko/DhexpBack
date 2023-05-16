package com.wevioo.demande.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.demande.enums.NatureCredit;
import com.wevioo.demande.enums.ObjetCredit;
import com.wevioo.demande.enums.TypeCredit;
import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
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
		private String nouveauPromoteur;
		private String statut;
		private String Utilisateur;
		private Date dateDeclaration;
		private Beneficiaire beneficiare;	
		private Projet projet;
		private Credit credit ; 
        private Partenaire partenaire ;
        private Autorisation autorisation;
        private Fond fond;
        private Modalite modalite ;	
        private Passifs passifs ;
        private Actifs actifs ;
		private MontantInvestissement montantInvestissement;
		private PieceJointes pieceJointes;
}
