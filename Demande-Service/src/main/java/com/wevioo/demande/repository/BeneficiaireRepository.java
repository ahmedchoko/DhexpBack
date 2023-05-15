package com.wevioo.demande.repository;

import com.wevioo.demande.entities.Beneficiaire;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BeneficiaireRepository extends PagingAndSortingRepository<Beneficiaire, Long> {
}