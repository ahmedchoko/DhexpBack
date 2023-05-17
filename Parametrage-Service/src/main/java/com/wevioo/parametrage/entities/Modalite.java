package com.wevioo.parametrage.entities;


import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wevioo.parametrage.enums.ModaliteStatut;
import com.wevioo.parametrage.enums.TypeDemande;
import com.wevioo.parametrage.enums.TypeModalite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Modalite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idModalite;
	@JsonIgnoreProperties("modalites")
	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private Fond fond ;
	@JsonIgnore
	@OneToMany(mappedBy="modalite",fetch = FetchType.EAGER)
	private List <Convention> conventions ;
	private String nomCompletModalite;

	private String nomArabeModalite;
	private String abrevModalite ;
	@Enumerated(EnumType.STRING)
	private ModaliteStatut statut ;
	private Long montantMin;
	private Long montantMax ;
	@Enumerated(EnumType.STRING)
	@Column(name = "modalite_type")
	private TypeModalite typeModalite;
	@Enumerated(EnumType.STRING)
	@Column(name = "demande_type")
	private TypeDemande natureDemande;
	public Modalite(Long idModalite, String nomCompletModalite) {
		super();
		this.idModalite = idModalite;
		this.nomCompletModalite = nomCompletModalite;
	}
}
