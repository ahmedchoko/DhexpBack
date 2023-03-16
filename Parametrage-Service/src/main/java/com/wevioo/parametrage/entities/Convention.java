package com.wevioo.parametrage.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	

	@ManyToOne()
	private Partenaire partenaire ; 
	
	@ManyToOne()
	private Modalite modalite ;

	@Override
	public String toString() {
		return "Convention [idConvention=" + idConvention + ", dateSignature=" + dateSignature + "]";
	}
	

}

