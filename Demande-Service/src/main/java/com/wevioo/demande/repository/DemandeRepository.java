package com.wevioo.demande.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.wevioo.demande.entities.Demande;
import org.springframework.stereotype.Repository;

@Repository

public interface DemandeRepository extends JpaRepository<Demande, Long> , JpaSpecificationExecutor<Demande>
{
	
}
