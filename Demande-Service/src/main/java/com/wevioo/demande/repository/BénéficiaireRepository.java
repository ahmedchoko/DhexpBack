package com.wevioo.demande.repository;

import com.wevioo.demande.entities.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BénéficiaireRepository extends PagingAndSortingRepository<Beneficiaire, Long> {
}
