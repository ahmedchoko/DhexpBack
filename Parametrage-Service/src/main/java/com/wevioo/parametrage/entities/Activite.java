package com.wevioo.parametrage.entities;

import java.util.List;

import javax.persistence.Entity;
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
public class Activite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAct;
	private String name;
	@JsonIgnore
	@ManyToOne()
	private SousSecteur sousSecteur ;

	@Override
	public String toString() {
		return "Activite [idActivite=" + idAct + ", name=" + name + "]";
	}

	public Long getIdAct() {
		return idAct;
	}

	public void setIdAct(Long idAct) {
		this.idAct = idAct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SousSecteur getSousSecteur() {
		return sousSecteur;
	}

	public void setSousSecteur(SousSecteur sousSecteur) {
		this.sousSecteur = sousSecteur;
	}
	
	
}
