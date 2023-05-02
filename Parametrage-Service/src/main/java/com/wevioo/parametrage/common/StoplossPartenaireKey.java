package com.wevioo.parametrage.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StoplossPartenaireKey implements Serializable {
    @Column(name = "idSL")
    Long idSL;

    @Column(name = "idPartenaire")
    Long idPartenaire;

   public StoplossPartenaireKey() {}

    public StoplossPartenaireKey(
            Long idSL,
            Long idPartenaire) {
        this.idSL = idSL;
        this.idPartenaire = idPartenaire;
    }

}
