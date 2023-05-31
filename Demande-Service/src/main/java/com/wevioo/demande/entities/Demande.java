package com.wevioo.demande.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Demande implements Serializable{


	private static final long serialVersionUID = 1L;
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
		private String utilisateur;
		private Date dateDeclaration;
		@OneToOne()
		private Beneficiaire beneficiare;
		@OneToOne()
		private Projet projet;
		@OneToOne()
		private Credit credit ;
		/*@OneToOne()
        private Autorisation autorisation;*/
		/*@OneToOne()
        private Passifs passifs ;
		@OneToOne()
        private Actifs actifs ;*/
		/*@OneToOne()
		private MontantInvestissement montantInvestissement;*/
		@OneToOne()
		private PieceJointes pieceJointes;
		@OneToOne()
	    private Modalite modalite ;
	/*	@OneToOne()
	    private Fond fond ;*/
		@OneToOne()
		private Partenaire partenaire ;


}
