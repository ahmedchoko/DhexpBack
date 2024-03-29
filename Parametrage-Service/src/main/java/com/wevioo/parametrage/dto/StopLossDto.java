package com.wevioo.parametrage.dto;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.enums.TypeStopLoss;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopLossDto {
        @Transient
        Long idSL;
        Fond fond;
        String nomSL ;
        Integer tauxSL ;
        Date dateValiditeSL ;
        Date dateFinSL ;
        Fondstatut statutSL;


}
