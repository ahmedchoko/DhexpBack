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
    @Column(name = "type_projet", nullable = true)
    @Enumerated(EnumType.STRING)
    private TypeProjet typeProjet;
    //code postal
    @Column(name = "code_postal", nullable = true)
    private Integer codePostal;

    //facteur risque
    @Column(name = "facteur_risque", nullable = true)
    private String facteurRisque;


    //site d'implantation
    @Column(name = "site", nullable = true)
    private String site;
    
    //demande associée au projet
    @JsonIgnore
	@OneToOne(mappedBy="projet")
    private Demande demande;
    //délégation du projet
    @Column(name="délégation", nullable = true)
	private String delegation;
}
