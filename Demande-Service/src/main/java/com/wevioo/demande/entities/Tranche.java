package com.wevioo.demande.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.demande.enums.TypeProjet;
import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Zone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tranche {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTranche")
	private Long idTranche;
    //numéro de la tranche
    @Column(name = "numéro_tranche", nullable = false)
    private Integer numeroTranche ;
    //liste des amortissements de la tranche
    @JoinColumn(name = "liste_amortissements", nullable = false)
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Amortissement> amortissement;
    @JoinColumn(name = "crédit_associé")
	@ManyToOne()
    private Credit credit;
}
