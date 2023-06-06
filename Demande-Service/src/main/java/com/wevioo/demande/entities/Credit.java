package com.wevioo.demande.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.demande.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_credit")
    private Long idCredit;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "type_credit")
    private TypeCredit typeCredit;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "nature_credit")
    private NatureCredit natureCredit;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "objet_credit")
    private ObjetCredit objetCredit;

    @NotNull
    @Column(name = "montant_credit")
    private Long montantCredit;

    @NotNull
    @Column(name = "duree_credit")
    private Long dureeCredit;

    @Column(name = "solde")
    private Integer solde;

    @Column(name = "montant_credit_exige")
    private Integer montantCreditExige;

    @Column(name = "montant_contribution_paye")
    private Integer montantContributionPaye;

    @Column(name = "surete_reel")
    @Size(max = 255)
    private String sureteReel;

    @Column(name = "marge")
    private Integer marge;

    @Column(name = "taux_interet")
    private Integer tauxInteret;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "nature_taux_interet")
    private NatureTauxInteret natureTauxInteret;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_echeance")
    private Date dateEcheance;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_autorisation")
    private Date dateAutorisation;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_derniere_tombe_principale")
    private Date dateDerniereTombePrincipale;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_premiere_tombe_principale")
    private Date datePremiereTombePrincipale;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "forme_remboursement")
    private FormeRomboursement formeRomboursement;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "periodicite")
    private Periodicite periodicite;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_deblocage")
    private Date dateDeblocage;

    @Column(name = "montant_credit_debloquee")
    private Long montantCreditDebloquee;

    @Column(name = "capital_social")
    private Long capitalSocial;

    @Column(name = "montant_credit_autorise")
    private Long montantCreditAutorise;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "eligible_riti")
    private Choix eligibleRITI;

    @Column(name = "schema_cofinancement_pool_bancaire")
    @Size(max = 255)
    private String schemaCofinancementPoolBancaire;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "cofinancement")
    private Choix cofinancement;

    @Column(name = "immobilisation_nettes_avant_nouvel_investissement")
    private Long immobilisationNettesAvantNouvelInvestissement;

    @Column(name = "montant_investissement")
    private Long montantInvestissement;

    @Column(name = "montantrisque")
    private Long montantrisque;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "nouveau_prometeur")
    private Choix nouveauPrometeur;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_entree_production")
    private Date dateEntreeProduction;

    @JsonIgnore
    @OneToOne(mappedBy = "credit")
    private Demande demande;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Tranche> tranches;
}
