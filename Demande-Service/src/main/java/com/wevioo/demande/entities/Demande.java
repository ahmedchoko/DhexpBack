package com.wevioo.demande.entities;

import java.util.Date;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Demande {

	    @Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long idDemande;
		private String numeroCompte ;
		private Long numeroPret ;
		private Long codeCentraleRisque ;
		private Long codedouane ;
		private String numeroRne ;
		private String referenceDemande;
		private String nouveauPromoteur;
		private String statut;
		private String Utilisateur;
		private Date dateDeclaration;
		//private String modaliteAcceptee;
		@OneToOne(cascade = {CascadeType.ALL})
		private Beneficiaire beneficiare;
		@OneToOne(cascade = {CascadeType.ALL})
		private Projet projet;
		@OneToOne(cascade = {CascadeType.ALL})
		private Credit credit ;
		@OneToOne(cascade = {CascadeType.ALL})
        private Autorisation autorisation;
		@OneToOne(cascade = {CascadeType.ALL})
        private Passifs passifs ;
		@OneToOne(cascade = {CascadeType.ALL})
        private Actifs actifs ;
		@OneToOne(cascade = {CascadeType.ALL})
		private MontantInvestissement montantInvestissement;
		@OneToOne(cascade = {CascadeType.ALL})
		private PieceJointes pieceJointes;
		@OneToOne()
	    private Modalite modalite ;
		@OneToOne()
	    private Fond fond ;
		@OneToOne()
		private Partenaire partenaire ;


}
