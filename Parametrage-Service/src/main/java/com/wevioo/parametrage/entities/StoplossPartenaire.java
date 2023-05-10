package com.wevioo.parametrage.entities;

import com.wevioo.parametrage.common.StoplossPartenaireKey;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.enums.TypeStopLoss;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class StoplossPartenaire implements Serializable {
   /* public StoplossPartenaire( Partenaire partenaire, StopLoss stoploss){
        this.partenaire = partenaire;
        this.stoploss = stoploss;
        this.idSLPartenaire = new StoplossPartenaireKey(partenaire.getIdPartenaire(), stoploss.getIdSL());
    }*/

    @EmbeddedId
    StoplossPartenaireKey idSLPartenaire;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idPartenaire")
    //@JoinColumn(name = "partenaire_id")
    Partenaire partenaire;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idSL")
    //@JoinColumn(name = "stoploss_id")
    StopLoss stoploss;

    @Override
    public int hashCode() {
        return Objects.hash(partenaire, stoploss);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StoplossPartenaire other = (StoplossPartenaire) obj;
        return Objects.equals(partenaire, other.partenaire) && Objects.equals(stoploss, other.stoploss);
    }
     public StoplossPartenaire() {}

    public StoplossPartenaire(  StoplossPartenaireKey idSLPartenaire, Partenaire partenaire, StopLoss stoploss, Date dateFinSLPart, Date dateValiditeSLPArt,
                               TypeStopLoss typeSLPart, Fondstatut statutSLPart, Integer tauxSLPartenaire) {
        this.partenaire = partenaire;
        this.stoploss = stoploss;
        this.idSLPartenaire = new StoplossPartenaireKey(partenaire.getIdPartenaire(), stoploss.getIdSL());
        this.dateFinSLPart = dateFinSLPart;
        this.dateValiditeSLPArt = dateValiditeSLPArt;
        this.typeSLPart = typeSLPart;
        this.statutSLPart = statutSLPart;
        this.tauxSLPartenaire = tauxSLPartenaire;
    }
    private Integer tauxSLPartenaire;
    private Date dateValiditeSLPArt ;
    private Date dateFinSLPart ;
    @Enumerated(EnumType.STRING)
    private TypeStopLoss typeSLPart;
    @Enumerated(EnumType.STRING)
    private Fondstatut statutSLPart;
}
