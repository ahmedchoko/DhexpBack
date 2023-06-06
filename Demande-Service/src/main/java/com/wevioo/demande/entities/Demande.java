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
		@Lob
		private byte[] tabAmortissement;
		@OneToOne(cascade = {CascadeType.MERGE})
		private Beneficiaire beneficiare;
		@OneToOne(cascade = {CascadeType.MERGE})
		private Projet projet;
		@OneToOne(cascade = {CascadeType.MERGE})
		private Credit credit ;
	    private Long modalite ;
		private Long partenaire ;


}
