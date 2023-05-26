package com.wevioo.parametrage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wevioo.parametrage.entities.SousSecteur;

@Repository
public interface SousSecteurRepository extends JpaRepository<SousSecteur, Long> {


}
