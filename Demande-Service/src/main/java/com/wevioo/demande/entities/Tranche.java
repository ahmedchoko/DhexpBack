package com.wevioo.demande.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wevioo.demande.enums.TypeProjet;
import com.wevioo.parametrage.entities.Delegation;
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
	private Long idTranche;
    private Integer numeroTranche ;
    @OneToMany(mappedBy="tranche",fetch = FetchType.EAGER)
    private Amortissement amortissement;
}
