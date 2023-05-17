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
    private TYPEPERSONNE typPersonne;
    private Long matriculeFiscale;
    //private Personne personne;
    private String natureActivite;
    private Integer codeActivite;
    @OneToOne
    private Secteur secteur;
    @OneToOne
    private SousSecteur sousSecteur;
    private String activite;
    private String region;
  @OneToOne
 private Gouvernorat gouvernorat;
 @OneToOne
  private Delegation delegation;
    private String adresse;
    private Integer codePostal;

}

