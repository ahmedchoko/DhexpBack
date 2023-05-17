package com.wevioo.demande.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.wevioo.demande.enums.FormeJuridique;
import com.wevioo.demande.enums.PieceIdentification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value="moral")
public class PersonneMorale extends Personne implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonneMorale;
	private String raisonSociale ;
	private FormeJuridique formeJuridique;
	@OneToOne()
	private Beneficiaire beneficiaire ;
}