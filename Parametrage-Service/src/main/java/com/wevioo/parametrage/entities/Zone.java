package com.wevioo.parametrage.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.enums.Choix;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class Zone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idZone;
	private String nomZone ;
	private String nomArabeZone ;
	@JsonIgnoreProperties("zones")
	@OneToOne(mappedBy="zone",fetch = FetchType.EAGER)
	private Quotite quotitee ;
	@JsonIgnore()
	@OneToMany(mappedBy="zone",fetch = FetchType.EAGER)
	Set<Delegation> delegations;
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

	public Set<Delegation> getDelegations() {
		return delegations;
	}
	public void setDelegations(Set<Delegation> delegations) {
		this.delegations = delegations;
	}
	public Zone(Long idZone, String nomZone, String nomArabeZone, Quotite quotitee, Set<Delegation> delegations) {
		super();
		this.idZone = idZone;
		this.nomZone = nomZone;
		this.nomArabeZone = nomArabeZone;
		this.delegations = delegations;
	}
	public Zone() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Quotite getQuotitee() {
		return quotitee;
	}
	public void setQuotitee(Quotite quotitee) {
		this.quotitee = quotitee;
	}

	
	
}
