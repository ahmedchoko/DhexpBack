package com.wevioo.demande.entities;

import java.io.Serializable;
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
public class Demande implements Serializable{


	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "idDemande")
		private Long idDemande;
		//numéro du compte du bénéficiaire
		@Column(name = "numéro_compte",nullable = false, unique = true )
		private String numeroCompte ;
	    //numéro de prêt
	    @Column(name = "numéro_prêt",nullable = false, unique = true )
		private String numeroPret ;
	   //code central des risques
	    @Column(name = "code_central_risque",nullable = false, unique = true )
		private String codeCentraleRisque ;
	    //code de douane
	    @Column(name = "code_douane",nullable = false, unique = true )
		private String codedouane ;
	    //numéro RNE
	    @Column(name = "numéro_rne",nullable = false, unique = true )
		private String numeroRne ;
	    //référence de la demande
	    @Column(name = "référence",nullable = false, unique = true )
		private String referenceDemande;
		//nouveau promoteur ou non
		@Column(name = "nouveau_promoteur", nullable = false)
		private String nouveauPromoteur;
		//statut de la deamnde
		@Column(name = "statut", nullable = false)
		private String statut;
		//utilisateur ayant créé la demande
		@Column(name = "chargé_saisie", nullable = false)
		private String utilisateur;
		//date de déclaration de la demande
		@Column(name = "date_déclaration", nullable = false)
		private Date dateDeclaration;
		//bénéficiaire
		@JoinColumn(name = "bénéficiaire")
		@OneToOne(cascade = {CascadeType.MERGE})
		private Beneficiaire beneficiaire;
		//projet faisant objet de la demande
		@JoinColumn(name = "projet")
		@OneToOne(cascade = {CascadeType.MERGE})
		private Projet projet;
		// crédit demandé
		@JoinColumn(name = "crédit")
		@OneToOne(cascade = {CascadeType.MERGE})
		private Credit credit ;
		//modalité de la demande
		@Column(name = "modalité", nullable = false)
	    private Long modalite ;
		//partenaire
		@Column(name = "partenaire", nullable = false)
		private Long partenaire ;
		//fichier tableau d'amortissement
		@Column(name = "amortissement")
		@Lob
	    private byte[] tabAmortissement;

}
