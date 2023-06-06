package com.wevioo.parametrage.entities;

import java.util.Date;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Convention {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="idConvention")
	private Long idConvention;
	//date de la signature de la convention
	@Column(name = "dateSignature")
	private Date dateSignature ; 
	//partenaire conventionné
	@JsonIgnoreProperties("conventions")
	@ManyToOne()
	private Partenaire partenaire ;
	//modalité conventionnée
	@ManyToOne(fetch = FetchType.EAGER)
	private Modalite modalite ;
	@Override
	public String toString() {
		return "Convention [idConvention=" + idConvention + ", dateSignature=" + dateSignature + "]";
	}
	public Convention(Partenaire partenaire) {
		super();
		this.partenaire = partenaire;
	}

}

