package com.wevioo.demande.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Passifs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPassifs;
    private Long capitalSocial;
    private Long reserve;
    private Long capitauxPropres;
    private Long resultatReporte;
    private Long totalCapitauxPropresAvRes;
    private Long ResExercice;
    private Long getTotalCapitauxPropres;
    private Long emprunt;
    private Long pasisifsFinanciers;
    private Long provisions;
    private Long totalActifsNonCour;
    private Long fourComptesRattaches;
    private Long passifsCour;
    private Long concoursBancaires;
    private Long totalPassifsCourants;
    private Long totalPassifs;
    private Long getTotalCapitauxPropresEtPassifs;

}