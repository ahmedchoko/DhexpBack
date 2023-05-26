package com.wevioo.demande.entities;

import com.wevioo.demande.enums.TypeProjet;
import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Zone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProjet;
    @Enumerated(EnumType.STRING)
    private TypeProjet typeProjet;
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
	@OneToOne(mappedBy="projet")
    private Demande demande;
    @OneToOne
	private Zone zone;
    @OneToOne
	private Delegation delegation;
}
