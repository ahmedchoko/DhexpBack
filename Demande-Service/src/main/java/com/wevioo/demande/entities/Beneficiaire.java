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
    @Column(name = "type_personne")
    @NotNull
    private TYPEPERSONNE typPersonne;

    @Column(name = "nature_activite")
    @NotNull
    @Size(max = 255)
    private String natureActivite;

    @Column(name = "numero_rib")
    @NotNull
    @Size(max = 255)
    private String numeroRib;

    @Column(name = "activite")
    @NotNull
    private Long activite;

    @Column(name = "region")
    @NotNull
    @Size(max = 255)
    private String region;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delegation_id")
    @NotNull
    private Delegation delegation;

    @Column(name = "adresse")
    @NotNull
    @Size(max = 255)
    private String adresse;

    @Column(name = "code_postal")
    @NotNull
    private Integer codePostal;

    @OneToOne(mappedBy = "beneficiaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private PersonneMorale personneMorale;

    @OneToOne(mappedBy = "beneficiaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private PersonnePhysique personnePhysique;
}
