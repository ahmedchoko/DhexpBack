package com.wevioo.demande.entities;

import java.util.Date;

import javax.persistence.*;

import com.wevioo.demande.enums.FormeRomboursement;
import com.wevioo.demande.enums.NatureCredit;
import com.wevioo.demande.enums.NatureTauxInteret;
import com.wevioo.demande.enums.ObjetCredit;
import com.wevioo.demande.enums.Periodicite;
import com.wevioo.demande.enums.TypeAutorisation;
import com.wevioo.demande.enums.TypeCredit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Autorisation {
	    @Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long idAutorisation;
		private Long numDeclarationAutori ;
	    @Enumerated(EnumType.STRING)
		private TypeAutorisation typeAutorisation ; 
}
