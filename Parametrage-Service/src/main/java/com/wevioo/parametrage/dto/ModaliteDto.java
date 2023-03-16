package com.wevioo.parametrage.dto;


import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.enums.*;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ModaliteDto {
     Integer idModalite;
     Fond fond;
     String nomCompletMod;
     String nomArabeMod;
     String abrevModalite;
     MadaliteStatut statut;
     long montantMin;
     long montantMax;
     TypeModalite typeModalite;
     TypeDemande natureDemande;
}
