package com.wevioo.parametrage.dto;

import com.wevioo.parametrage.common.StoplossPartenaireKey;
import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.entities.StopLoss;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.enums.TypeStopLoss;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class StoplossPartenaireDto {
    StoplossPartenaireKey idSLPartenaire;
    Partenaire partenaire;
    StopLoss stoploss;
    Integer tauxSLPartenaire;
    //Date dateValiditeSLPArt;
    //Date dateFinSLPart;
    TypeStopLoss typeSLPart;
    Fondstatut statutSLPart;

}
