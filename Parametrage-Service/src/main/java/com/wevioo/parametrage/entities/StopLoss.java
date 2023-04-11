package com.wevioo.parametrage.entities;

import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.enums.TypePatenaire;
import com.wevioo.parametrage.enums.TypeStopLoss;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
}
