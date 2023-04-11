package com.wevioo.parametrage.dto;

import java.util.Date;
import java.util.List;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Secteur;
import com.wevioo.parametrage.entities.Zone;
import com.wevioo.parametrage.enums.Fondstatut;

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
	private List<Zone> zones ;
	private List<Fond> fonds ;

}
