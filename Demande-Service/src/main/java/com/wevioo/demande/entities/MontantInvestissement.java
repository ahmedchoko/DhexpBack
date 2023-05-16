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

public class MontantInvestissement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMontant;
    private Long fraisEtablissement;
    private Long terrain;
    private Long genieCivilAmenag;
    private Long equipProd;
    private Long matBureau;
    private Long matTransport;
    private Long fraisAppDivers;
    private Long fondsRoulement;
    private Long resDeuxDerBilans;
    private Long capAugcap;
    private Long autoFinancement;
    private Long foprodiRiti;
    private Long ppqf;
    private Long sicar;
    private Long ccAssocies;
    private Long creditTerme;
    private Long creditLeasing;
    private Long creditExterieur;
    private Long creditCT;
    private Long montantContribution;


}
