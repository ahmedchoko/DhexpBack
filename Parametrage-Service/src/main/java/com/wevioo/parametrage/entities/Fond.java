package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@OneToMany()
	private List<Modalite> modalites ;
	@Override
	public String toString() {
		return "Fond [idFond=" + idFond + ", montantMax=" + montantMax + ", dateClotureFond=" + dateClotureFond
				+ ", tresorerieFond=" + tresorerieFond + ", secteur=" + secteur + "]";
	}


	
}
