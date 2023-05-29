package com.wevioo.demande.entities;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Zone;

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
    @JsonIgnore
    @OneToOne(mappedBy="actifs")
    public Demande demande ;
    @OneToOne
    private Zone zone;
    @OneToOne
    private Delegation delegation;
}
