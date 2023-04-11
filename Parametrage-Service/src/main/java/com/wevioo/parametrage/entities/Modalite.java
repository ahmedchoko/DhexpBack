package com.wevioo.parametrage.entities;


import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
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
	@ManyToOne(fetch = FetchType.EAGER)
	private Fond fond ;
	@JsonIgnore
	@OneToMany(mappedBy="modalite",fetch = FetchType.EAGER)
	private List <Convention> conventions ;
	@NotNull
	private String nomCompletModalite;
	@NotNull
	private String nomArabeModalite;
	@NotNull
	private String abrevModalite ;
	@Enumerated(EnumType.STRING)
	@NotNull
	private ModaliteStatut statut ;
	@NotNull
	private Long montantMin;
	@NotNull
	private Long montantMax ;
	@Enumerated(EnumType.STRING)
	@Column(name = "modalite_type")
	@NotNull
	private TypeModalite typeModalite;
	@Enumerated(EnumType.STRING)
	@Column(name = "demande_type")
	@NotNull
	private TypeDemande natureDemande;

}
