package com.wevioo.parametrage.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Delegation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idDelegation;
	
	private String nomDelegation ;
	///@JsonIgnoreProperties("delegations")
	@JsonIgnore()
	@ManyToOne()
	public Zone zone ;
	public Long getIdDelegation() {
		return idDelegation;
	}
	public void setIdDelegation(Long idDelegation) {
		this.idDelegation = idDelegation;
	}
	public String getNomDelegation() {
		return nomDelegation;
	}
	public void setNomDelegation(String nomDelegation) {
		this.nomDelegation = nomDelegation;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public Delegation(Long idDelegation, String nomDelegation, Zone zone) {
		super();
		this.idDelegation = idDelegation;
		this.nomDelegation = nomDelegation;
		this.zone = zone;
	}
	public Delegation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
