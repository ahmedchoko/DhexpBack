package com.wevioo.demande.entities;

import com.wevioo.demande.enums.TYPEPERSONNE;
import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Gouvernorat;
import com.wevioo.parametrage.entities.Secteur;
import com.wevioo.parametrage.entities.SousSecteur;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Beneficiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBeneficiaire;
    private TYPEPERSONNE typPersonne;
    private Long matriculeFiscale;
    //private Personne personne;
    private String natureActivite;
    private Integer codeActivite;
    private Secteur secteur;
    private SousSecteur sousSecteur;
    private String activite;
    private String region;
    private Gouvernorat gouvernorat;
    private Delegation delegation;
    private String adresse;
    private Integer codePostal;

}

