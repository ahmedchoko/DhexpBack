package com.wevioo.parametrage.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	private Long idAct;
	private String name;
	@JsonIgnoreProperties("activites")
	@ManyToOne(fetch = FetchType.EAGER)
	private SousSecteur sousSecteur ;
}
