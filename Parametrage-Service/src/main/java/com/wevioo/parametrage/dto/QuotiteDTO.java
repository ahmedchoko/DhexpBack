package com.wevioo.parametrage.dto;

import java.util.List;

import com.wevioo.parametrage.entities.Fond;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class QuotiteDTO {
	private Long idQuotite;
	private String zonal ;
	private String ritic ;
	private String nouvPromo ;
	private String creditLeas ;
	private Long valeurAppl ;
	private List<ZoneDTO> zones ;
	private List<FondDTO> fonds ;

}
