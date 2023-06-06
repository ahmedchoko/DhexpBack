package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.enums.Fondstatut;
import com.wevioo.parametrage.enums.TypePatenaire;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Partenaire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idPartenaire")
	private Long idPartenaire;
	//type du partenaire
	@Column(name ="type" , nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private TypePatenaire typePartenaire ;
	//nom complet
	@Column(name ="nom_complet" , nullable = true, unique = false)
	private String nomCompletPartenaire ;
	//nom en Arabe
	@Column(name ="nom_arabe" , nullable = true, unique = false)
	private String nomArabePartenaire ;
	//abréviation du partenaire
	@Column(name ="abréviation" , nullable = false, unique = true)
	private String abrevPartenaire ;
	//adresse
	@Column(name ="adresse" , nullable = false, unique = false)
	private String adresse ;
	//numéro téléphone
	@Column(name ="téléphone" , nullable = false, unique = true)
	private Long numTelephone ;
	//numéro fax
	@Column(name ="fax" , nullable = true, unique = true)
	private Long numFax ;
	//adresse email
	@Column(name ="email" , nullable = false, unique = true)
	private String mail ;
	//code BIC
	@Column(name ="code_bic" , nullable = false, unique = true)
	private String codeBic ;
	//Site web
	@Column(name ="site_web" , nullable = false, unique = true)
	private String site ;
	//date d'activation du partenaire
	@Column(name ="date_activation" , nullable = false, unique = false)
	private Date dateActivation ;
	//date de blocage du partenaire
	@Column(name ="date_blocage" , nullable = false, unique = false)
	private Date dateBlocage ;
	//Statut du partenaire
	@Column(name ="statut" , nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private Fondstatut statut ;
	//liste des conventions du partenaire
	@Column(name = "liste_conventions")
	@JsonIgnoreProperties("partenaire")
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="partenaire")
	private List<Convention> conventions ;
	///liste des stoploss
	@Column(name = "liste_stoploss")
	@JsonIgnoreProperties("partenaire")
	@OneToMany(mappedBy="stopLoss", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@Transient
	private Set<StoplossPartenaire> stoplosses ;
	public Partenaire(String nomCompletPartenaire) {
		super();
		this.nomCompletPartenaire = nomCompletPartenaire;
	}

}

