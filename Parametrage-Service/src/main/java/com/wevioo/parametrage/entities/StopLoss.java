package com.wevioo.parametrage.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.enums.Fondstatut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopLoss {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idSL")
    private Long idSL;
    //fond du stoploss
    @Column(name = "fond")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fond_id")
    private Fond fond;
    //nom du stoploss
    @Column(name = "nom_stoploss", nullable = false, unique = true)
    private String nomSL ;
    //taux du stoploss
    @Column(name = "taux_stoploss", nullable = false, unique = false)
    private Integer tauxSL ;
    //date de démarrage du stoploss
    @Column(name = "date_démarrage", nullable = false, unique = true)
    //date de fin du stoploss
    private Date dateValiditeSL ;
    @Column(name = "date_fin", nullable = false, unique = true)
    private Date dateFinSL ;
    //statut du stoploss
    @Column(name = "statut", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private Fondstatut statutSL;
    //partenaires du stoploss
    @Column(name = "liste_partenaires")
    @JsonIgnoreProperties("stoplosses")
    @OneToMany(mappedBy = "partenaire",fetch = FetchType.EAGER,  cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private Set<StoplossPartenaire> partenaires;
}
