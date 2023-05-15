package com.wevioo.demande.repository;

import com.wevioo.demande.entities.MontantInvestissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MontantInvestissementRepository extends PagingAndSortingRepository<MontantInvestissement, Long> {
}
