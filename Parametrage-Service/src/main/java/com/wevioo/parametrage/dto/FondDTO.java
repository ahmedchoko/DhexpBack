package com.wevioo.parametrage.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Quotite;
import com.wevioo.parametrage.entities.Secteur;
import com.wevioo.parametrage.enums.Fondstatut;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FondDTO {

	private Long idFond;
	private String nomFond ;
	private String nomCompletFond ;
	private String nomArabeFond ;
	private String abrevFond ;
	private Fondstatut statut ; 
	private Long montantMax ;
	private Long montantMin ;
	private Date dateDemarrageFond;
	private Date dateClotureFond;
	private Long tresorerieFond;
	private List<Secteur> secteurs ;
	private String codes ;
	private Set <Modalite> modalites ;
	private Set <Quotite> quotites ;

	
}


