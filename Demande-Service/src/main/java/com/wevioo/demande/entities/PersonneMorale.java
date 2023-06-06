package com.wevioo.demande.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.demande.enums.FormeJuridique;
import com.wevioo.demande.enums.PieceIdentification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value="moral")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonneMorale extends Personne implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Personne")
	private Long idPersonneMorale;
	//raison social du bénéficiaire moral
	@Column(name="raison_social", nullable = false)
	private String raisonSociale ;
	//forme juridique du bénéficiaire
	@Column(name = "forme_juridique", nullable = false)
	@Enumerated(EnumType.STRING)
	private FormeJuridique formeJuridique;
	//bénéficiaire

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
