package com.wevioo.parametrage.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wevioo.parametrage.entities.Fond;

public interface FondRepository extends JpaRepository<Fond, Long> {
	 String FILTER_FONDS_ON_FIRST_NAME_QUERY = "select f from Fond f where UPPER(f.nomCompletFond) like CONCAT('%',UPPER(?1),'%')";
	 @Query(FILTER_FONDS_ON_FIRST_NAME_QUERY)
	 Page <Fond> findByFirstNameLike(String firstNameFilter,Pageable pageable);
}
