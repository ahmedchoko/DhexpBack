package com.wevioo.parametrage.entities;


import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
@Data
public class Zone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idZone")
	private Long idZone;
	//code de la zone
	@Column(name = "code_zone", nullable = false, unique = true)
	private String codeZone;
	//nom de la zone
	@Column(name = "nom", nullable = false, unique = true)
	private String nomZone ;
	//nom en Arabe
	@Column(name = "nom_arabe", nullable = true, unique = true)
	private String nomArabeZone ;
	//liste des gouvernorats dans la zone

	@OneToMany(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("listeDelegations")
	private Set<Gouvernorat> gouvernorats;
	//liste des délégations dans la zone

	@OneToMany(fetch = FetchType.EAGER)
	private Set<Delegation> delegations;
	//quotité de la zone

	@JsonIgnoreProperties("zone")
	@OneToOne(mappedBy="zone",fetch = FetchType.EAGER)
	private Quotite quotitee ;



}
