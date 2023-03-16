package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.enums.TypePatenaire;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Partenaire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPartenaire;
	
	private TypePatenaire typePartenaire ;
	private String nomCompletPartenaire ;
	private String nomArabePartenaire ;
	private String abrevPartenaire ; 
	private String adresse ;
	private Long numTelephone ; 
	private Long numFax ; 
	private String mail ; 
	private String codeBic ;
	private String site ;
	private Date dateActivation ;
	private Date dateBlocage ; 
	private Fondstatut statut ;
	@OneToMany()
	private List<Convention> conventions ;
}
