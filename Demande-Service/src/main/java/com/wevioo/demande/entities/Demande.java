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
		@Column(name = "numéro_compte",nullable = true, unique = true )
		private String numeroCompte ;
	   //code central des risques
	    @Column(name = "code_central_risque",nullable = true, unique = true )
		private String codeCentraleRisque ;
	    //code de douane
	    @Column(name = "code_douane",nullable = true, unique = true )
		private String codedouane ;
	    //numéro RNE
	    @Column(name = "numéro_rne",nullable = true, unique = true )
		private String numeroRne ;
		//nouveau promoteur ou non
		@Column(name = "nouveau_promoteur", nullable = true)
		private String nouveauPromoteur;
		//statut de la deamnde
		@Column(name = "statut", nullable = true)
		private String statut;
		//utilisateur ayant créé la demande
		@Column(name = "chargé_saisie", nullable = true)
		private String utilisateur;
		//date de déclaration de la demande
		@Column(name = "date_déclaration", nullable = true)
		private Date dateDeclaration;
		//bénéficiaire

		@OneToOne(cascade = {CascadeType.MERGE})
		private Beneficiaire beneficiare;
		//projet faisant objet de la demande

		@OneToOne(cascade = {CascadeType.MERGE})
		private Projet projet;
		// crédit demandé

		@OneToOne(cascade = {CascadeType.MERGE})
		private Credit credit ;
		//modalité de la demande
		@Column(name = "modalité", nullable = true)
	    private String modalite ;
		@Column(name = "fond", nullable = true)
	    private String fond ;
		//partenaire
		@Column(name = "partenaire", nullable = true)
		private String partenaire ;
		//fichier tableau d'amortissement
		@Column(name = "amortissement")
		@Lob
	    private byte[] tabAmortissement;

}
