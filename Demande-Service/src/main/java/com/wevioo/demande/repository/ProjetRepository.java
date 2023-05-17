package com.wevioo.demande.repository;

import com.wevioo.demande.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProjetRepository extends PagingAndSortingRepository<Projet, Long> {
}
