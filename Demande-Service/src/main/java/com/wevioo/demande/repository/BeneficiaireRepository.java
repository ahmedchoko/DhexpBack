package com.wevioo.demande.repository;

import com.wevioo.demande.entities.Beneficiaire;
import com.wevioo.demande.entities.Credit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Long>
{
	
}
