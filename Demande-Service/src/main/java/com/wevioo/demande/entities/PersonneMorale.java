package com.wevioo.demande.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.wevioo.demande.enums.FormeJuridique;
import com.wevioo.demande.enums.PieceIdentification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
public class PersonneMorale extends Personne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonneMorale;
	private String raisonSociale ;
	private FormeJuridique formeJuridique;
}