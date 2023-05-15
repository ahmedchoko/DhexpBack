package com.wevioo.parametrage.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.enums.Choix;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Zone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idZone;
	private String nomZone ;
	private String nomArabeZone ;

	@OneToMany(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("listeDelegations")
	private Set<Gouvernorat> gouvernorats;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Delegation> delegations;

	@JsonIgnoreProperties("zones")
	@OneToOne(mappedBy="zone",fetch = FetchType.EAGER)
	private Quotite quotitee ;
	
}
