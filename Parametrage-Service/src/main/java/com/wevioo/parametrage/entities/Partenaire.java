package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	@Enumerated(EnumType.STRING)
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
	@Enumerated(EnumType.STRING)
	private Fondstatut statut ;
	 @JsonIgnoreProperties("partenaire")
	@OneToMany(mappedBy="partenaire",fetch = FetchType.LAZY)
	private List<Convention> conventions ;
	@JsonIgnoreProperties("partenaire")
	@OneToMany(mappedBy="stopLoss", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@Transient
	private Set<StoplossPartenaire> stoplosses ;
	public Partenaire(String nomCompletPartenaire) {
		super();
		this.nomCompletPartenaire = nomCompletPartenaire;
	}

}