package com.wevioo.demande.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.wevioo.demande.enums.FormeJuridique;
import com.wevioo.demande.enums.PieceIdentification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value="moral")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonneMorale extends Personne implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonneMorale;
	private String raisonSociale ;
	@Enumerated(EnumType.STRING)
	private FormeJuridique formeJuridique;
	private Long matriculeFiscale;
	@OneToOne()
	private Beneficiaire beneficiaire ;
}