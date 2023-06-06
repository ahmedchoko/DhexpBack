package com.wevioo.demande.entities;

import com.wevioo.demande.enums.TYPEPERSONNE;
import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Gouvernorat;
import com.wevioo.parametrage.entities.Secteur;
import com.wevioo.parametrage.entities.SousSecteur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Beneficiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idBeneficiaire;
    @Enumerated(EnumType.STRING)
    private TYPEPERSONNE typPersonne;
    private String natureActivite;
	private String numeroRib ;
    private String activite;
    private String region;
    @OneToOne
    private Delegation delegation;
    private String adresse;
    private Integer codePostal;
    @OneToOne(mappedBy="beneficiaire")
    private PersonneMorale personneMorale;
    @OneToOne(mappedBy="beneficiaire")
    private PersonnePhysique personnePhysique;


}
