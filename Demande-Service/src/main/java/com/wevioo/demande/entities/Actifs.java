package com.wevioo.demande.entities;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Actifs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idActifs;
    private Long immoIncorporelles;
    private Long amort;
    private Long immoCorporelles;
    private Long immoFinancieres;
    private Long autresActNonCourants;
    private Long totalActNonCourants;
    private Long stocks;
    private Long clientsComlptesRattaches;
    private Long placementsAutresActNonFin;
    private Long liquiditesEtEquivalents;
    private Long totalActCourants;
    private Long totalActifs;

}
