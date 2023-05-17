package com.wevioo.parametrage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.enums.ModaliteStatut;
import com.wevioo.parametrage.enums.TypeDemande;
import com.wevioo.parametrage.enums.TypeModalite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Gouvernorat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idGouv;
    private String nomGouv;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Delegation> listeDelegations;
    @JsonIgnore
    @ManyToOne
    private Zone zone;
	public Gouvernorat(Long idGouv, String nomGouv) {
		super();
		this.idGouv = idGouv;
		this.nomGouv = nomGouv;
	}

    
}
