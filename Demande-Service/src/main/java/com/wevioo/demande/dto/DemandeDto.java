package com.wevioo.demande.dto;

import com.wevioo.demande.enums.*;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Modalite;
import com.wevioo.parametrage.entities.Partenaire;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DemandeDto {
    Fond fond;
    Modalite modalite;
    Partenaire partenaire;
    String numeroCompte;
    String numeroPret;
    String codeCentraleRisque;
    String codedouane;
    String numeroRne;
    String typeCredit;
    String objetCredit;
    Integer dureeCredit;
    String natureCredit;
    String typPersonne;
    Integer codeActivite;
    Long montantInvestissement;
    Long immobilisationNettesAvantNouvelInvestissement;
    Long montantCreditAutorise;
    String nouveauPromoteur;
    String raisonSociale;
    String formeJuridique;
    String matriculeFiscale;
    String nomPrenom;
    Long telephone;
    String peiceIdenti;
    String numeroPiece;
}
