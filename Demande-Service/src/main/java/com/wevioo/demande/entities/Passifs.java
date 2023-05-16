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