package com.wevioo.demande.dto;

import java.util.Date;
import java.util.List;

import com.wevioo.demande.entities.Actifs;
import com.wevioo.demande.entities.Demande;
import com.wevioo.demande.enums.ObjetCredit;
import com.wevioo.demande.enums.TYPEPERSONNE;
import com.wevioo.demande.enums.TypeCredit;
import com.wevioo.demande.enums.TypeProjet;
import com.wevioo.parametrage.dto.ConventionDTO;
import com.wevioo.parametrage.dto.ModaliteDto;
import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Zone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DemandePreliminaireDTO {

	
	private String codecentralerisques;
	private String codedouane;
	private String codeprofession;
	private Date datedeblocage;
	private String dureecredit;
	private String fond;
	private String modalite;
	private Long montantcreditautorise;
	private Long montantinvestissement;
	private String nomCompletPartenaire;
	private String nouvPromo;
	
	private String numerocompte ; 
	private String numerorne;
	 
    private ObjetCredit objetcredit;
    private String referencedossierpartenaire;
    
    private String ritic ; 
    private String typePartenaire; 
    
    private TYPEPERSONNE typebenificiaire;
    private TypeCredit typecredit;
    
    private String typefinancement;
    private TypeProjet typeprojet ;
	
}
