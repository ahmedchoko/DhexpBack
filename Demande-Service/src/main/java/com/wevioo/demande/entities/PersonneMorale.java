package com.wevioo.demande.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.demande.enums.FormeJuridique;
import com.wevioo.demande.enums.PieceIdentification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value="moral")
 @NoArgsConstructor @AllArgsConstructor @Builder
public class PersonneMorale extends Personne implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonneMorale;
	private String raisonSociale ;
	private FormeJuridique formeJuridique;
	@JsonIgnore
	@OneToOne()
	private Beneficiaire beneficiaire ;
	public Long getIdPersonneMorale() {
		return idPersonneMorale;
	}
	public void setIdPersonneMorale(Long idPersonneMorale) {
		this.idPersonneMorale = idPersonneMorale;
	}
	public String getRaisonSociale() {
		return raisonSociale;
	}
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}
	public FormeJuridique getFormeJuridique() {
		return formeJuridique;
	}
	public void setFormeJuridique(FormeJuridique formeJuridique) {
		this.formeJuridique = formeJuridique;
	}
	public Beneficiaire getBeneficiaire() {
		return beneficiaire;
	}
	public void setBeneficiaire(Beneficiaire beneficiaire) {
		this.beneficiaire = beneficiaire;
	}
	
}