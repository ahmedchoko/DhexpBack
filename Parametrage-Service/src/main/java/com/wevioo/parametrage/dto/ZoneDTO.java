package com.wevioo.parametrage.dto;

import java.util.Date;
import java.util.Set;

import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Gouvernorat;
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
	private String codeZone;
	private String nomZone ;
	private String nomArabeZone ;
	Set<Gouvernorat> gouvernorats;
	Set<Delegation> delegations;
}
