package com.wevioo.demande.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.wevioo.demande.enums.TypeAutorisation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Amortissement {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAmortissement;
    
    private String numeroEcheance ;
    private Date dateEcheance;
	private Long montantAmortissement;
	private Long montantEcheance ;
	private Long restantDu ;
	private String statutsEcheance ;
	
	
    @ManyToOne()
	private Tranche tranche ; 
}
