package com.wevioo.demande.repository;

import com.wevioo.demande.entities.Actifs;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActifsRepository extends PagingAndSortingRepository<Actifs, Long> {
}
