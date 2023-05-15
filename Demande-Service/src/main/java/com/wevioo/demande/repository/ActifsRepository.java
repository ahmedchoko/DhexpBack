package com.wevioo.demande.repository;

import com.wevioo.demande.entities.Actifs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActifsRepository extends PagingAndSortingRepository<Actifs, Long> {
}
