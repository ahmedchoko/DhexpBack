package com.wevioo.demande.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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