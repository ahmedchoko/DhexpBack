package com.wevioo.parametrage.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wevioo.parametrage.entities.Fond;

@Repository
public interface FondRepository extends JpaRepository<Fond, Long> , JpaSpecificationExecutor<Fond>  {
	/* String FILTER_FONDS_ON_FIRST_NAME_QUERY = "select f from Fond f where UPPER(f.nomCompletFond) like CONCAT('%',UPPER(?1),'%') AND f.montantMax = ?2 AND f.montantMin = ?3 AND UPPER(f.statut) like CONCAT('%',UPPER(?4),'%')";
	 @Query(FILTER_FONDS_ON_FIRST_NAME_QUERY)
	 Page <Fond> findByFirstNameLike(String FondsearchTerm,Long MontantMaxsearchTerm,Long MontantMinsearchTerm,String StatutsearchTerm,Pageable pageable);
	*/
	 
	 String FILTER_FONDS = "select f from Fond f";
	 @Query(FILTER_FONDS)
	 Page <Fond> findAllFond(Pageable pageable);
/*	 
	 
	 String FONDS ="SELECT f FROM Fond f JOIN Modalite m on f.idFond==m.fond.idFond group by f.idFond ";
	 @Query(FONDS)
	List <Fond> AllFond();*/
}
