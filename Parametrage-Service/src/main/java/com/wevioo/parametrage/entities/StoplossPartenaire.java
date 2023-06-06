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

public class StoplossPartenaire implements Serializable {

    @EmbeddedId
    StoplossPartenaireKey idSLPartenaire;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idPartenaire")
    Partenaire partenaire;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("idSL")
    StopLoss stoploss;
    //taux du stoploss
    @Column(name = "taux_sl_partenaire",nullable = false, unique = false)
    private Integer tauxSLPartenaire;
    //date de démarrage du stoploss
    @Column(name = "date_dém_sl_partenaire",nullable = false, unique = false)
    private Date dateValiditeSLPArt ;
    //date de fin du stoploss
    @Column(name = "date_fin_sl_partenaire",nullable = false, unique = false)
    private Date dateFinSLPart ;
    //type du stoploss
    @Column(name = "type_sl_partenaire",nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private TypeStopLoss typeSLPart;
    //statut du stoploss
    @Column(name = "statut_sl_partenaire",nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private Fondstatut statutSLPart;
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

}
