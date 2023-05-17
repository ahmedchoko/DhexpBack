package com.wevioo.demande.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PieceJointes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPieceJointes;
	private String etatFinancierDernierExercice;
	private String rapportCommisaireCompte ;
	private String dossierJuridique  ;
	private String planFinancementInvestissement  ;
	private String planFinancement   ;
	private String comptesRésultatsPrévisionnels;
	private String contratPret;
	private String titreCredit;
	private String nouveauPromoteur;
	private String tabAmortissement;
	private String contratOuvRenouvCredit;
	@OneToOne(mappedBy="pieceJointes")
	public Demande demande  ;

}
