package com.wevioo.parametrage.dto;

import java.util.Date;
import java.util.List;


import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.enums.TypePatenaire;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PartenaireDTO {
	
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
	private List<Convention> conventions ;
	private List<Fond> fonds ;
}
