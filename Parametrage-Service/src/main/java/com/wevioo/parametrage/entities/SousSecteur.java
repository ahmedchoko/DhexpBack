package com.wevioo.parametrage.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class SousSecteur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idSousSecteur;
	private String name;
    @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Secteur secteur ; 
	@OneToMany(mappedBy="sousSecteur",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	  private Set<Activite> activites;

	@Override
	public String toString() {
		return "SousSecteur [idSousSecteur=" + idSousSecteur + ", name=" + name + "]";
	}

	public Long getIdSousSecteur() {
		return idSousSecteur;
	}

	public void setIdSousSecteur(Long idSousSecteur) {
		this.idSousSecteur = idSousSecteur;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public Set<Activite> getActivites() {
		return activites;
	}

	public void setActivites(Set<Activite> activites) {
		this.activites = activites;
	}
	
}
