package com.wevioo.parametrage.dto;

import java.util.Date;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.enums.Fondstatut;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneDTO {
	private Long idZone;
	private String nomZone ;
	private String nomArabeZone ;
}
