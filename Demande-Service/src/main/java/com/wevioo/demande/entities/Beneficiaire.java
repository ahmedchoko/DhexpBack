package com.wevioo.demande.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.wevioo.demande.enums.FormeRomboursement;
import com.wevioo.demande.enums.NatureCredit;
import com.wevioo.demande.enums.NatureTauxInteret;
import com.wevioo.demande.enums.ObjetCredit;
import com.wevioo.demande.enums.Periodicite;
import com.wevioo.demande.enums.TYPEPERSONNE;
import com.wevioo.demande.enums.TypeAutorisation;
import com.wevioo.demande.enums.TypeCredit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Beneficiaire {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long idBeneficiaire;
	    private TYPEPERSONNE typePersonne;
	    private Long matriculeFiscale;

	    @OneToOne(mappedBy="beneficiaire")
	    private PersonneMorale personneMorale;
	    @OneToOne(mappedBy="beneficiaire")
	    private PersonnePhysique personnePhysique;
	    private String natureActivite;
	    private Integer codeActivite;
	    private String secteur;
	    private String soussecteur;
	    private String activite;
	    private String region;
	    private String gouvernorat;
	    private String delegation;
	    private String adresse;
	    private Integer codePostal;

	    
}
