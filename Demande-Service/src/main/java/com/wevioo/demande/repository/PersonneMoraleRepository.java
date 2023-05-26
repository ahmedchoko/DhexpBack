package com.wevioo.demande.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wevioo.demande.entities.PersonneMorale;
import com.wevioo.demande.entities.PersonnePhysique;

public interface PersonneMoraleRepository extends JpaRepository<PersonneMorale, Long>
{
	
}

