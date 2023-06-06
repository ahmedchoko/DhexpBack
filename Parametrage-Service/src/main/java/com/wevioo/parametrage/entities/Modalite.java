package com.wevioo.parametrage.entities;


import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.wevioo.parametrage.enums.ModaliteStatut;
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
	@Column(name="idModalite")
	private Long idModalite;
	@JsonIgnoreProperties("modalites")
	//fond auquel appartient la modalité
	@ManyToOne(fetch = FetchType.EAGER)
	private Fond fond ;
	//liste des conventions
	@JsonIgnore
	@OneToMany(mappedBy="modalite")
	private List <Convention> conventions ;
	//nom complet de la modalité
	@Column(name = "nom_complet", nullable = false, unique = true)
	private String nomCompletModalite;
	//nom en arabe de la modalité
	@Column(name = "nom_arabe", nullable = true, unique = true)
	private String nomArabeModalite;
	@Column(name = "abréviation", nullable = true, unique = true)
	//abréviation
	private String abrevModalite ;
	//Statut de la modalité
	@Enumerated(EnumType.STRING)
    @Column(name="statut", nullable = false, unique = false)
	private ModaliteStatut statut ;
	//montant minimum
	@Column(name="montant_min", nullable = false, unique = false)
	private Long montantMin;
	//montant maximum
	@Column(name="montant_max", nullable = false, unique = false)
	private Long montantMax ;
	//type de la modalité
	@Enumerated(EnumType.STRING)
	@Column(name = "type_modalité", nullable = false, unique = false)
	private TypeModalite typeModalite;
	//type de la demande
	@Enumerated(EnumType.STRING)
	@Column(name = "demande_type", nullable = false, unique = false)
	private TypeDemande natureDemande;
	public Modalite(Long idModalite, String nomCompletModalite) {
		super();
		this.idModalite = idModalite;
		this.nomCompletModalite = nomCompletModalite;
	}
}
