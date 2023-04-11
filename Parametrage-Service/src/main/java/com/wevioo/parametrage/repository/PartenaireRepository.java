package com.wevioo.parametrage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wevioo.parametrage.entities.Fond;
import com.wevioo.parametrage.entities.Partenaire;

@Repository
public interface PartenaireRepository  extends JpaRepository<Partenaire, Long>, JpaSpecificationExecutor<Partenaire>   {

	
	String FILTER_PARTENAIRE_ON_FIRST_NAME_QUERY = "select p from Partenaire p where UPPER(p.nomCompletPartenaire) like CONCAT('%',UPPER(?1),'%')";
	 @Query(FILTER_PARTENAIRE_ON_FIRST_NAME_QUERY)
	 Page <Partenaire> findByFirstNameLike(String firstNameFilter,Pageable pageable);
	 
	

}
