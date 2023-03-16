package com.wevioo.parametrage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wevioo.parametrage.entities.Partenaire;

public interface PartenaireRepository  extends JpaRepository<Partenaire, Long> {

	
	String FILTER_PARTENAIRE_ON_FIRST_NAME_QUERY = "select p from Partenaire p where UPPER(p.nomCompletPartenaire) like CONCAT('%',UPPER(?1),'%')";
	 @Query(FILTER_PARTENAIRE_ON_FIRST_NAME_QUERY)
	 Page <Partenaire> findByFirstNameLike(String firstNameFilter,Pageable pageable);
}
