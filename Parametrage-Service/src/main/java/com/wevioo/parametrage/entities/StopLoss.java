package com.wevioo.parametrage.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.common.StoplossPartenaireKey;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.enums.TypePatenaire;
import com.wevioo.parametrage.enums.TypeStopLoss;
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
    private Long idSL;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fond_id")
    private Fond fond;
    private String nomSL ;
    private Integer tauxSL ;
    private Date dateValiditeSL ;
    private Date dateFinSL ;
    @Enumerated(EnumType.STRING)
    private Fondstatut statutSL;
    @JsonIgnoreProperties("stoplosses")
    @OneToMany(mappedBy = "partenaire",fetch = FetchType.EAGER,  cascade = CascadeType.ALL, orphanRemoval = true)
    @Transient
    private Set<StoplossPartenaire> partenaires;
}
