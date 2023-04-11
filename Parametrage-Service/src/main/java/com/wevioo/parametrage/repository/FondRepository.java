package com.wevioo.parametrage.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.wevioo.parametrage.entities.Fond;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FondRepository extends JpaRepository<Fond, Long>, JpaSpecificationExecutor<Fond>
{
	String FILTER_FONDS = "select f from Fond f";
	@Query(FILTER_FONDS)
	Page <Fond> findAllFond(Pageable pageable);
	String FILTER_ARCHIVED_FONDS = "select f from Fond f where UPPER(f.statut) <> UPPER('archive')";
	@Query(FILTER_ARCHIVED_FONDS)
	List<Fond> getNonArchivedFonds();
}
