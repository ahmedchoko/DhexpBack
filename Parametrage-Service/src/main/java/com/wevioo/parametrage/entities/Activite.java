package com.wevioo.parametrage.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Activite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idActivite")
	private Long idAct;
	//code de l'activité
	@Column(name="codeActivite", unique = true)
	private String codeActivite;
	//nom de l'activité
	@Column(name = "nomActivite", nullable = false)
	private String name;
	//sous secteur de l'activité
	@JsonIgnoreProperties("activites")
	@ManyToOne(fetch = FetchType.EAGER)
	@Column(name = "sousSecteur")
	private SousSecteur sousSecteur ;
}
