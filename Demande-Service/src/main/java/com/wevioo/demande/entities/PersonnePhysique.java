package com.wevioo.demande.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.demande.enums.PieceIdentification;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue(value="physique")

public class PersonnePhysique extends Personne implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonnePhysique;
	private String nom ;
	private String prenom;
	private PieceIdentification peiceIdenti;
	private Long numeroPiece;
	private String telephone;
	@OneToOne()
	private Beneficiaire beneficiaire ;
}
