package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.enums.TypePatenaire;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
 
public class Secteur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idSec;
	private String name;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL,mappedBy="secteurs")
	  private Set<Fond> fonds;

	@OneToMany(mappedBy="secteur",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@Fetch(value=FetchMode.SELECT)
	  private Set<SousSecteur> sousSecteurs;
	@Override
	public String toString() {
		return "Secteur [ name=" + name + "]";
	}
	public Long getIdSec() {
		return idSec;
	}
	public void setIdSec(Long idSec) {
		this.idSec = idSec;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Fond> getFonds() {
		return fonds;
	}
	public void setFonds(Set<Fond> fonds) {
		this.fonds = fonds;
	}
	public Set<SousSecteur> getSousSecteurs() {
		return sousSecteurs;
	}
	public void setSousSecteurs(Set<SousSecteur> sousSecteurs) {
		this.sousSecteurs = sousSecteurs;
	}
	
	
}
