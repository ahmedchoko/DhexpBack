package com.wevioo.parametrage.dto;

import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Gouvernorat;
import com.wevioo.parametrage.enums.Fondstatut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneDto {
        @Transient
        Long idZone;
        String nomZone ;
        String nomArabeZone;
        Set<Gouvernorat> gouvernorats;
        Set<Delegation> delegations;


}
