package com.wevioo.parametrage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wevioo.parametrage.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByUserkeycloakId(String userkeycloakId); 

	Utilisateur findByEmail(String email);

	Utilisateur findByTelephone(String telephone);

    @Query("SELECT u FROM Utilisateur u WHERE u.userkeycloakId = :userkeycloakId AND u.idUtilisateur <> :idUtilisateur")
	Utilisateur findByUserkeycloakIdExceptId(Long idUtilisateur, String userkeycloakId);

	
    @Query("SELECT u FROM Utilisateur u WHERE u.telephone = :telephone AND u.idUtilisateur <> :idUtilisateur")
	Utilisateur findByTelephoneExceptId(Long idUtilisateur, String telephone);

	
    @Query("SELECT u FROM Utilisateur u WHERE u.email = :email AND u.idUtilisateur <> :idUtilisateur")
	Utilisateur findByEmailExceptId(Long idUtilisateur, String email);

    @Query("SELECT u FROM Utilisateur u WHERE u.cin = :cin AND u.idUtilisateur <> :idUtilisateur")
	Utilisateur findByCinExceptId(Long idUtilisateur, String cin);

}
