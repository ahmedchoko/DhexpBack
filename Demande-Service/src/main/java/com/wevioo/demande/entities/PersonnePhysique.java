package com.wevioo.demande.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wevioo.demande.enums.PieceIdentification;

@Entity
public class PersonnePhysique extends Personne {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonnePhysique;
	private String nom ;
	private String prenom;
	private PieceIdentification peiceIdenti;
	private Long numeroPiece;
	private String telephone;
}
