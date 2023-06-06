package com.wevioo.parametrage.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.wevioo.parametrage.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
