package com.wevioo.demande.repository;

import com.wevioo.demande.entities.Beneficiaire;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BeneficiaireRepository extends PagingAndSortingRepository<Beneficiaire, Long> {
}
