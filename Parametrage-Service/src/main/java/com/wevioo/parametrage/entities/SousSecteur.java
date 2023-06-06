package com.wevioo.parametrage.entities;



import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class SousSecteur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idSousSecteur")
	private Long idSousSecteur;
	//nom du sous secteur
	@Column(name="nom_ssec", nullable = false, unique = true)
	private String name;
	//nom du secteur
	@Column(name = "nom_sec")
	@JsonIgnoreProperties("sousSecteurs")
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Secteur secteur ;
	//liste des activites du secteur
	@Column(name = "Liste_activites")
    @JsonIgnoreProperties("sousSecteur")
	@OneToMany(mappedBy="sousSecteur",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	  private Set<Activite> activites;
	public SousSecteur(Long idSousSecteur, String name, Secteur secteur, Set<Activite> activites) {
		super();
		this.idSousSecteur = idSousSecteur;
		this.name = name;
		this.secteur = secteur;
		this.activites = activites;
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
	public SousSecteur() {
		super();
		// TODO Auto-generated constructor stub
	}

}
