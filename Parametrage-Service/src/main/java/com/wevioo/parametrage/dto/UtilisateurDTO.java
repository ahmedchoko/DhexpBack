package com.wevioo.parametrage.dto;

import java.util.Date;
import java.util.Set;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurDTO {
	private Long idUtilisateur;
	private String userkeycloakId ;
	private String nom;
	private String prenom;
	private Long telephone ; 
	private String email ;
	private Date dateEntree ; 
	private String username;
	private Long cin;
	private String typeStructure;
	private String typePartenaire;
	private String photo ;
	private String country ; 
	private String ville ; 
	private Long zip ; 
	private String roleUser ; 
	private String groupUser ; 
	private boolean activatedAccount ; 
	private String infos;
}
