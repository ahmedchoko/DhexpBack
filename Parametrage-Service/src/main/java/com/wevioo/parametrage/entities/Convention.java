package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private Long idConvention;
	private Date dateSignature ; 
	
	@JsonIgnoreProperties("conventions")
	@ManyToOne()
	private Partenaire partenaire ; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Modalite modalite ;
	@Override
	public String toString() {
		return "Convention [idConvention=" + idConvention + ", dateSignature=" + dateSignature + "]";
	}

}

