package com.wevioo.demande.dto;

import com.wevioo.demande.entities.Projet;
import com.wevioo.parametrage.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandeDto {
    private String idDemande;
    private String typePartenaire;
  private String nomCompletPartenaire;
   private String numerocompte;
    private String codecentralerisques;
    private String beneficiaire;
    private String matriculeFiscal;
    private String nomCompletBenificiare;
    private String typePieceIdentification;
    private String numPieceIdentification;
    private String genre;
   private String telephonefixe;
   private String telephoneMobile;
    private String mail;
    private String profession;
    private String natureActivite;
    private String activites;
   private Integer codePostal;
    private String addresse;
    private String delegation;
    private String region;
   private String raisonSociale;
   private String formeJuridique;
   private String fond;
    private String modalite;
    private String dateDeclaration;
    private String utilisateur;
   private String typeProjet;
    private String nouveauPromoteur;
  private String cofinancement;
   private String delegationProjet;
   private String codePostalProjet;
    private String dateEntreeProduction;
    private String montantInvestissement;
   private String numeroTranche;
  private String dureeCredit;
    private String objetCredit;
   private String typeCredit;
    private String montantCredit;
  private String periodicite;
    private String formeRomboursement;
    private String dateDeblocage;
   private String montantRisque;
    private String zoneImplementation;
}
