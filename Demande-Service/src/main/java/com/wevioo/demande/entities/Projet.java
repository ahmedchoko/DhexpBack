package com.wevioo.demande.entities;

import com.wevioo.demande.enums.TypeProjet;
import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Zone;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProjet;
    private TypeProjet typeProjet;
    private Zone zone;
    private Delegation delegation;
    private Integer codePostal;
    private String garanties;
    private String facteurRisque;
    private String norme;
    private String moyenProduction;
    private String approvisionnement;
    private String envConcurrentiel;
    private String potentielMarche;
    private String strategieComm;
    private String site;
    private String prodOuServices;

}
