package com.wevioo.demande.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wevioo.demande.entities.Beneficiaire;
import com.wevioo.demande.entities.PersonnePhysique;

public interface PersonnePhysiqueRepository extends JpaRepository<PersonnePhysique, Long>
{
	
}
