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
    @Column(name = "type_credit", nullable = true)
    private TypeCredit typeCredit;

    @Enumerated(EnumType.STRING)
    @Column(name = "nature_credit", nullable = true)
    private NatureCredit natureCredit;

    @Enumerated(EnumType.STRING)
    @Column(name = "objet_credit", nullable = true)
    private ObjetCredit objetCredit;

    
    @Column(name = "montant_credit", nullable = true)
    private Long montantCredit;

    @Column(name = "duree_credit", nullable = true)
    private Long dureeCredit;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_autorisation", nullable = true)
    private Date dateAutorisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "forme_remboursement", nullable = true)
    private FormeRomboursement formeRomboursement;

    @Enumerated(EnumType.STRING)
    @Column(name = "periodicite", nullable = true)
    private Periodicite periodicite;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_deblocage", nullable = true)
    private Date dateDeblocage;

    @Column(name = "montant_credit_debloquee", nullable = true)
    private Long montantCreditDebloquee;

    @Column(name = "montant_credit_autorise", nullable = true)
    private Long montantCreditAutorise;


    @Enumerated(EnumType.STRING)
    @Column(name = "cofinancement", nullable = true)
    private Choix cofinancement;

    @Column(name = "montant_investissement", nullable = true)
    private Long montantInvestissement;

    @Column(name = "montantrisque", nullable = true)
    private Long montantrisque;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_entree_production")
    private Date dateEntreeProduction;

    @JsonIgnore
    @OneToOne(mappedBy = "credit")
    private Demande demande;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Tranche> tranches;
}
