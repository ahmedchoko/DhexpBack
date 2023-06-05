package com.wevioo.demande.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.demande.enums.PieceIdentification;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue(value="physique")
 @NoArgsConstructor @AllArgsConstructor @Builder
public class PersonnePhysique extends Personne implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonnePhysique;
	private String nomCompletBenificiare;
	private PieceIdentification typePieceIdentification;
	private String numPieceIdentification;
	private String genre ;
	private String telephonefixe;
	private String telephoneMobile1;
	private String telephoneMobile2;
	private String mail;

	@JsonIgnore
	@OneToOne()
	private Beneficiaire beneficiaire ;
	public Long getIdPersonnePhysique() {
		return idPersonnePhysique;
	}
	public void setIdPersonnePhysique(Long idPersonnePhysique) {
		this.idPersonnePhysique = idPersonnePhysique;
	}
	public Beneficiaire getBeneficiaire() {
		return beneficiaire;
	}
	public void setBeneficiaire(Beneficiaire beneficiaire) {
		this.beneficiaire = beneficiaire;
	}
	public String getNomCompletBenificiare() {
		return nomCompletBenificiare;
	}
	public void setNomCompletBenificiaire(String nomCompletBenificiare) {
		this.nomCompletBenificiare = nomCompletBenificiare;
	}
	public PieceIdentification getTypePieceIdentification() {
		return typePieceIdentification;
	}
	public void setTypePieceIdentification(PieceIdentification typePieceIdentification) {
		this.typePieceIdentification = typePieceIdentification;
	}
	public String getNumPieceIdentification() {
		return numPieceIdentification;
	}
	public void setNumPieceIdentification(String numPieceIdentification) {
		this.numPieceIdentification = numPieceIdentification;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getTelephonefixe() {
		return telephonefixe;
	}
	public void setTelephonefixe(String telephonefixe) {
		this.telephonefixe = telephonefixe;
	}
	public String getTelephoneMobile1() {
		return telephoneMobile1;
	}
	public void setTelephoneMobile1(String telephoneMobile1) {
		this.telephoneMobile1 = telephoneMobile1;
	}
	public String getTelephoneMobile2() {
		return telephoneMobile2;
	}
	public void setTelephoneMobile2(String telephoneMobile2) {
		this.telephoneMobile2 = telephoneMobile2;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

}
