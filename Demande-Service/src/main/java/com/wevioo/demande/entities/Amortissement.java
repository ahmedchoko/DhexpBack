package com.wevioo.demande.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.wevioo.demande.enums.TypeAutorisation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Amortissement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_amortissement")
    private Long idAmortissement;
    
    @Column(name = "numero_echeance")
    @Size(max = 255)
    private String numeroEcheance;
    
    @Column(name = "date_echeance")
    private Date dateEcheance;
    
    @Column(name = "montant_amortissement")
    private Long montantAmortissement;
    
    @Column(name = "montant_echeance")
    private Long montantEcheance;
    
    @Column(name = "restant_du")
    private Long restantDu;
    
    @Column(name = "statuts_echeance")
    @Size(max = 255)
    private String statutsEcheance;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Tranche tranche;

}
