package com.wevioo.parametrage.dto;

import java.util.Date;
import java.util.List;


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
