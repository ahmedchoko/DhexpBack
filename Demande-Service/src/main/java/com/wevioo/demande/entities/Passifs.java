package com.wevioo.demande.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private Long totalCapitauxPropres;
    private Long emprunt;
    private Long passifsFinanciers;
    private Long provisions;
    private Long totalActifsNonCour;
    private Long fourComptesRattaches;
    private Long passifsCour;
    private Long concoursBancaires;
    private Long totalPassifsCourants;
    private Long totalPassifs;
    private Long totalCapitauxPropresEtPassifs;

}