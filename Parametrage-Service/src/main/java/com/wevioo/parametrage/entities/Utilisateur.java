package com.wevioo.parametrage.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

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
	@Column(name = "idUtilisateur")
	private Long idUtilisateur;
	
	// l'identifiant keycloak de l'utilisateur
	@Column(name = "user_Keycloak_ID", nullable = false, unique = true)
	private String userkeycloakId ;
	
	// le nom de l'utilisateur
	@Column(name = "nom", nullable = true, unique = false)
	private String nom;
	
	// le prenom de l'utilisateur
	@Column(name = "prenom", nullable = true, unique = false)
	private String prenom;
	
	// le username de l'utilisateur
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	// la carte d'identité de l'utilisateur
	@Pattern(regexp = "\\d{8}", message = "CIN must contain exactly 8 digits")
	@Column(name = "cin", nullable = true, unique = true)
	private String cin;
	
	// type de structure a laquelle il appratient
	@Column(name = "type_Structure", nullable = false, unique = false)
	private String typeStructure;
	
	// type du partenaire ( Banque .. )
	@Column(name = "type_Partenaire", nullable = false, unique = false)
	private String typePartenaire;
	
	// photo de profil de l'utilisateur
	@Column(name = "photo", nullable = true, unique = false)
	private String photo ; 
	
	// telephone de l'utilisateur
	@Pattern(regexp = "^(5[0-9]{7}|2[0-9]{7}|9[0-9]{7})$", message = "Invalid telephone number format.")
	@Column(name = "telephone", nullable = true, unique = true)
	private String telephone ; 
	
	// email de l'utilisateur
	@Pattern(regexp = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b", message = "Invalid email format.")
	@Column(name = "email", nullable = false, unique = true)
	private String email ;
	
	// la date d'embauche 
	@Column(name = "date_Entree", nullable = true, unique = false)
	private Date dateEntree ; 
	
	// ville de l'utilisateur 
	@Column(name = "ville", nullable = true, unique = false)
	private String ville ; 
	
	// zip postal
	@Column(name = "zip", nullable = true, unique = false)
	private Long zip ; 
	
	// role de l'utilisateur ( admin , verif N1 .. )
	@Column(name = "role_User", nullable = false, unique = false)
	private String roleUser ; 
	
	// groupe de l'utilisateur : par exemple la banque ou il appartient
	@Column(name = "group_User", nullable = false, unique = false)
	private String groupUser ; 
	
	// des informations complémentaires sur l'utilisateur
	@Column(name = "infos", nullable = true, unique = false)
	private String infos;
	
	// statut de son compte
	@Column(name = "activated_Account", nullable = false, unique = false)
	private boolean activatedAccount ;
}
