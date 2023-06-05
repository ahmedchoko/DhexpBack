package com.wevioo.demande.dto;

import com.wevioo.demande.entities.Projet;
import com.wevioo.demande.enums.*;
import com.wevioo.parametrage.entities.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*interface Projet {
     String idDeleg = new String();
     String nomDeleg = new String();
}
interface Delegation {
    String idDeleg = new String();
    String nomDeleg = new String();

}
interface Activites {
    String idAct = new String();
    String name = new String();
    Object sousSecteur  = new Object() {
        String idSousSecteur = new String();
        String sousSecteur = new String();
        Object secteur =  new Object(){
            String idSec = new String();
            String name = new String();
        };
    };
  }*/
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
    private String telephoneMobile1;
    private String telephoneMobile2;
    private String mail;
    private String profession;
    private String natureActivite;
    private Activite activites;
    private String codePostal;
    private String addresse;
    private Delegation delegation;
    private String region;
    private String raisonSociale;
    private String formeJuridique;
    private String fond;
    private String modalite;
    private String dateDeclaration;
    private String referenceDemande;
    private String statut;
    private String utilisateur;
    private String NumeroDeclarationAutorisation;
    private String typeAutorisation;
    private String typeProjet;
    private String nouveauPromoteur;
    private String ritic;
    private String cofinancement;
    private Projet delegationProjet;
    private String codePostalProjet;
    private String dateEntreeProduction;
    private String montantInvestissement;
    private String referenceDossierpartenaire;
    private String numeroTranche;
    private String dureeCredit;
    private String objetCredit;
    private String typeCredit;
    private String montantCredit;
    private String tauxGarantie;
    private String montantGarantie;
    private String quotiteGarantie;
    private String montantEngagement;
    private String periodicite;
    private String formeRomboursement;
    private String montantCreditt;
    private String dateDerniereTombeePrincipale;
    private String datePremiereTombeePrincipale;
    private String dateDeblocage;
    private String montantRisque;
    private String montantGarantieDebloque;
    private String zoneImplementation;
}
