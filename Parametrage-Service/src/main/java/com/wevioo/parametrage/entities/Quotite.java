package com.wevioo.parametrage.entities;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.enums.Choix;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Quotite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idQuotite;
	
	@Enumerated(EnumType.STRING)
	private Choix zonal;
	
	@Enumerated(EnumType.STRING)
	private Choix ritic;
	
	@Enumerated(EnumType.STRING)
	private Choix nouvPromo;
	
	@Enumerated(EnumType.STRING)
	private Choix creditLeas;
	
	private int valeurAppl;
	@JsonIgnoreProperties("quotitee")
	@OneToOne()
	private Zone zone ;
	@JsonIgnoreProperties("quotites")
	@ManyToOne()
	private Fond  fond  ;
	
}
