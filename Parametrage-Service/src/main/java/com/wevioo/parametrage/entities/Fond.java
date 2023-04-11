package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.parametrage.enums.Fondstatut;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Fond {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idFond;
	private String nomCompletFond ;
	private String nomArabeFond ;
	private String abrevFond ;
	@Enumerated(EnumType.STRING)
	private Fondstatut statut ; 
	private Long montantMax ;
	private Long montantMin ;
	private Date dateDemarrageFond;
	private Date dateClotureFond;
	private Long tresorerieFond;
	private String secteur ;
	private String sousSecteur ;
	private String familleActivite ;
	private String codes ;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fond")
	//@JsonBackReference
	@JsonIgnore
	private List<Modalite> modalites ;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fond")
	@JsonIgnore
	private List<StopLoss> stopLosses ;


	
}
