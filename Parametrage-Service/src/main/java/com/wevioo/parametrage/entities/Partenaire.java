package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

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
	@Column(name ="adresse" , nullable = false, unique = true)
	private String adresse ;
	
	
	//mail
	@Pattern(regexp = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b", message = "Invalid email format.")
	@Column(name = "email", nullable = false, unique = true)
	private String mail;

	// site
	@Pattern(regexp = "^(http://|https://)?([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$", message = "Invalid website format.")
	@Column(name = "site_web", nullable = false, unique = true)
	private String site;

	// numero Telephone
	@Pattern(regexp = "^(5[0-9]{7}|2[0-9]{7}|9[0-9]{7})$", message = "Invalid telephone number format.")
	@Column(name = "téléphone", nullable = false, unique = true)
	private String numTelephone;

	// numero Fax
	@Pattern(regexp = "^\\d{8}$", message = "Invalid fax number format.")
	@Column(name = "fax", nullable = true, unique = true)
	private String numFax;
	
	//code BIC
    @Pattern(regexp = "^\\d{2}$", message = "Invalid code BIC format. It should contain exactly two digits.")
	@Column(name ="code_bic" , nullable = false, unique = true)
	private String codeBic ;
    
    
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

	@JsonIgnoreProperties("partenaire")
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="partenaire")
	private List<Convention> conventions ;
	///liste des stoploss

	@JsonIgnoreProperties("partenaire")
	@OneToMany(mappedBy="stopLoss", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@Transient
	private Set<StoplossPartenaire> stoplosses ;
	public Partenaire(String nomCompletPartenaire) {
		super();
		this.nomCompletPartenaire = nomCompletPartenaire;
	}

}

