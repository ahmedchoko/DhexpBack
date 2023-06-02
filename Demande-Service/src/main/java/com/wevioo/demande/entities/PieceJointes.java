package com.wevioo.demande.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Lob
	private byte[] etatFinancierDernierExercice;
	@Lob
	private byte[] rapportCommisaireCompte ;
	@Lob
	private byte[] dossierJuridique  ;
	@Lob
	private byte[] planFinancementInvestissement  ;
	@Lob
	private byte[] planFinancement   ;
	@Lob
	private byte[] comptesRésultatsPrévisionnels;
	@Lob
	private byte[] contratPret;
	@Lob
	private byte[] titreCredit;
	@Lob
	private byte[] nouveauPromoteur;
	@Lob
	private byte[] tabAmortissement;
	@Lob
	private byte[] contratOuvRenouvCredit;
    @JsonIgnore
	@OneToOne(mappedBy="pieceJointes")
	public Demande demande  ;

}
