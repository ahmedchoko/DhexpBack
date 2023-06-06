package com.wevioo.demande.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "idProjet")
    private Long idProjet;
    //type du projet
    @Column(name = "type_projet", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeProjet typeProjet;
    //code postal
    @Column(name = "code_postal", nullable = false)
    private Integer codePostal;
    //garanties ??? projet
    @Column(name = "garanties", nullable = false)
    private String garanties;
    //facteur risque
    @Column(name = "facteur_risque", nullable = false)
    private String facteurRisque;
    //norme du projet
    @Column(name = "norme", nullable = false)
    private String norme;
    //moyen de production
    @Column(name = "moyen_production", nullable = true)
    private String moyenProduction;
    //approvisionnement
    @Column(name = "approvisionnement", nullable = true)
    private String approvisionnement;
    //environnement concurrentiel
    @Column(name = "env_concurrentiel", nullable = true)
    private String envConcurrentiel;
    //potentiel marché
    @Column(name = "potentiel_marché", nullable = true)
    private String potentielMarche;
    //stratégie de communication
    @Column(name = "stratégie_comm", nullable = true)
    private String strategieComm;
    //site d'implantation
    @Column(name = "site", nullable = true)
    private String site;
    //production ou services
    @Column(name = "prod_ou_serv", nullable = true)
    private String prodOuServices;
    //demande associée au projet
    @JoinColumn(name = "demande")
    @JsonIgnore
	@OneToOne()
    private Demande demande;
    //délégation du projet
    @Column(name="délégation", nullable = false)
	private Long delegation;
}
