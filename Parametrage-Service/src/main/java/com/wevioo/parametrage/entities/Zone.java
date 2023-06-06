package com.wevioo.parametrage.entities;


import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
 @NoArgsConstructor @AllArgsConstructor @Builder
public class Zone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idZone")
	private Long idZone;
	//nom de la zone
	@Column(name = "nom", nullable = false, unique = true)
	private String nomZone ;
	//nom en Arabe
	@Column(name = "nom_arabe", nullable = true)
	private String nomArabeZone ;
	//liste des gouvernorats dans la zone
	@Column(name = "liste_gouvernorats")
	@OneToMany(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("listeDelegations")
	private Set<Gouvernorat> gouvernorats;
	//liste des délégations dans la zone
	@Column(name = "liste_délégations")
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Delegation> delegations;
	//quotité de la zone
	@Column(name = "quotité_appliquée")
	@JsonIgnoreProperties("zone")
	@OneToOne(mappedBy="zone",fetch = FetchType.EAGER)
	private Quotite quotitee ;

	public Long getIdZone() {
		return idZone;
	}

	public void setIdZone(Long idZone) {
		this.idZone = idZone;
	}

	public String getNomZone() {
		return nomZone;
	}

	public void setNomZone(String nomZone) {
		this.nomZone = nomZone;
	}

	public String getNomArabeZone() {
		return nomArabeZone;
	}

	public void setNomArabeZone(String nomArabeZone) {
		this.nomArabeZone = nomArabeZone;
	}

	public Set<Gouvernorat> getGouvernorats() {
		return gouvernorats;
	}

	public void setGouvernorats(Set<Gouvernorat> gouvernorats) {
		this.gouvernorats = gouvernorats;
	}

	public Set<Delegation> getDelegations() {
		return delegations;
	}

	public void setDelegations(Set<Delegation> delegations) {
		this.delegations = delegations;
	}

	public Quotite getQuotitee() {
		return quotitee;
	}

	public void setQuotitee(Quotite quotitee) {
		this.quotitee = quotitee;
	}

}
