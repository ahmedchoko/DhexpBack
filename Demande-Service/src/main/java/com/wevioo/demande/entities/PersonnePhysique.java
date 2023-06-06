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
	@Column(name = "idPersonne")
	private Long idPersonnePhysique;
	//nom complet du bénéficiaire physique
	@Column(name = "nom_complet", nullable = false)
	private String nomCompletBenificiare;
	//type de la pièce d'identification
	@Column(name = "pièce_identification", nullable = false)
	private PieceIdentification typePieceIdentification;
	//numéro de la pièce d'identification
	@Column(name = "numéro_identification", nullable = false, unique = true)
	private String numPieceIdentification;
	//sexe du bénéficiare physique
	@Column(name = "genre", nullable = false)
	private String genre ;
	//téléphone du bénéficiaire
	@Column(name = "téléphone_fixe", nullable = true)
	private String telephonefixe;
	//téléphone mobile du bénéficiaire
	@Column(name = "téléphone_mobile1", nullable = false, unique = true)
	private String telephoneMobile1;
	//Autre téléphone mobile du bénéficiaire
	@Column(name = "téléphone_mobile2", nullable = false)
	private String telephoneMobile2;
	//adresse email du bénéficiaire
	@Column(name = "email", nullable = false, unique = true)
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
