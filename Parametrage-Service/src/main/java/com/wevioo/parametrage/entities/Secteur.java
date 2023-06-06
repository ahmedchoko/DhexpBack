package com.wevioo.parametrage.entities;


import java.util.Set;

import javax.persistence.*;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
public class Secteur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idSecteur")
	private Long idSec;
	//nom du secteur
	@Column(name = "nom", nullable = false, unique = true)
	private String name;
	//fonds du secteur
	@Column(name = "fonds")
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL,mappedBy="secteurs")
	//liste des sous secteurs du secteur
	private Set<Fond> fonds;
	@Column(name = "sous_secteurs")
	@JsonIgnoreProperties("secteur")
	@OneToMany(mappedBy="secteur",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@Fetch(value=FetchMode.SELECT)
	private Set<SousSecteur> sousSecteurs;
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
	public Secteur(Long idSec, String name, Set<Fond> fonds, Set<SousSecteur> sousSecteurs) {
		super();
		this.idSec = idSec;
		this.name = name;
		this.fonds = fonds;
		this.sousSecteurs = sousSecteurs;
	}
	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}

}
