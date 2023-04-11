package com.wevioo.parametrage.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.entities.Secteur;
import com.wevioo.parametrage.enums.Fondstatut;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ConventionDTO {

	private Long idConvention;
	private Date dateSignature ; 
	private List <ModaliteDto> modalites ;
}
