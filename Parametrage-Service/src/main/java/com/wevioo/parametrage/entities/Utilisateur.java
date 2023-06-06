package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wevioo.parametrage.enums.Fondstatut;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUtilisateur;
	private String userkeycloakId ;
	private String nom;
	private String prenom;
	private String username;
	private Long cin;
	private String typeStructure;
	private String typePartenaire;
	private String photo ; 
	private Long telephone ; 
	private String email ;
	private Date dateEntree ; 
	private String country ; 
	private String ville ; 
	private Long zip ; 
	private String roleUser ; 
	private String groupUser ; 
	private String infos;
	private boolean activatedAccount ;
}
