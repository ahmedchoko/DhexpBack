package com.wevioo.parametrage.dto;


import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.enums.*;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ModaliteDto {
     Long idModalite;
     Fond fond;
     String nomCompletModalite;
     String nomArabeModalite;
     String abrevModalite;
     ModaliteStatut statut;
     Long montantMin;
     Long montantMax;
     List<Convention> conventions ;
     TypeModalite typeModalite;
     TypeDemande natureDemande;
}
