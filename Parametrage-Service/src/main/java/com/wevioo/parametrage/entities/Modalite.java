package com.wevioo.parametrage.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.parametrage.enums.MadaliteStatut;
import com.wevioo.parametrage.enums.TypeDemande;
import com.wevioo.parametrage.enums.TypeModalite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Modalite {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idModalite;
	@ManyToOne
	private Fond fond ;
	@OneToMany()
	private List <Convention> conventions ;
	private String nomCompletModalite;
	private String nomArabeModalite;
	private String abrevModalite ;
	@Enumerated(EnumType.STRING)
	private MadaliteStatut statut ; 
	private Long montantMin;
	private Long montantMax ;
	  @Enumerated(EnumType.STRING)
	    @Column(name = "modalite_type")
	    private TypeModalite typeModalite;
	    @Enumerated(EnumType.STRING)
	    @Column(name = "demande_type")
	    private TypeDemande natureDemande;
	/*@Override
	public String toString() {
		return "Modalite [idModalite=" + idModalite + ", fond=" + fond + ", abrevModalite=" + abrevModalite
				+ ", statut=" + statut + ", montantMin=" + montantMin + ", montantMax=" + montantMax + "]";
	} */
 
}
