package com.wevioo.demande.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.demande.enums.PieceIdentification;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue(value="physique")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonnePhysique extends Personne implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonnePhysique;
	private String nomPrenom ;
	@Enumerated(EnumType.STRING)
	private PieceIdentification peiceIdenti;
	private Long numeroPiece;
	private String telephone;
	@OneToOne()
	private Beneficiaire beneficiaire ;
}
