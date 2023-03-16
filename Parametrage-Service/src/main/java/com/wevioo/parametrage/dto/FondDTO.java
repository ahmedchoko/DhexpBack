package com.wevioo.parametrage.dto;

import java.util.Date;
import java.util.List;

import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.enums.Fondstatut;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FondDTO {

	private Long idFond;
	private String nomCompletFond ;
	private String nomArabeFond ;
	private String abrevFond ;
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
	private List<Modalite> modalites ;
}
