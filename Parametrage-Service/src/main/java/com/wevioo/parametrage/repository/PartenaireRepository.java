package com.wevioo.parametrage.repository;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;


import com.wevioo.parametrage.entities.Partenaire;


@Repository
public interface PartenaireRepository  extends JpaRepository<Partenaire, Long>, JpaSpecificationExecutor<Partenaire>   {

	
	String FILTER_PARTENAIRE_ON_FIRST_NAME_QUERY = "select p from Partenaire p where UPPER(p.nomCompletPartenaire) like CONCAT('%',UPPER(?1),'%')";
	 @Query(FILTER_PARTENAIRE_ON_FIRST_NAME_QUERY)
	 Page <Partenaire> findByFirstNameLike(String firstNameFilter,Pageable pageable);
	 
	 @Query(" select p.typePartenaire,count(p) from Partenaire p group by p.typePartenaire")
     List NobrePartenaireParType();
	 
	 @Query(" select count(p.idPartenaire) from Partenaire p")
     int NombreTotalPartenaire();

	
}
