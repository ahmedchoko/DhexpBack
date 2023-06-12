package com.wevioo.demande.entities;

import com.wevioo.demande.enums.TYPEPERSONNE;
import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Gouvernorat;
import com.wevioo.parametrage.entities.Secteur;
import com.wevioo.parametrage.entities.SousSecteur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beneficiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_beneficiaire")
    private Long idBeneficiaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_personne", nullable = true)
    private TYPEPERSONNE typPersonne;

    @Column(name = "nature_activite", nullable = true)
    @Size(max = 255)
    private String natureActivite;

    @Column(name = "numero_rib", nullable = true)
    @Size(max = 255)
    private String numeroRib;

    @Column(name = "activite", nullable = true)
    private Long activite;

    @Column(name = "region", nullable = true)
    @Size(max = 255)
    private String region;


    @Column(name = "delegation_id", nullable = true)
    private Long delegationId;
    
    @Column(name = "adresse", nullable = true)
    @Size(max = 255)
    private String adresse;

    @Column(name = "code_postal", nullable = true)
    private Integer codePostal;

    @OneToOne(mappedBy = "beneficiaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private PersonneMorale personneMorale;

    @OneToOne(mappedBy = "beneficiaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private PersonnePhysique personnePhysique;
}
