package com.wevioo.parametrage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Secteur;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long> {


}
