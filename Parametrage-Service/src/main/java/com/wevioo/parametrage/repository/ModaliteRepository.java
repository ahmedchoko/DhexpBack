package com.wevioo.parametrage.repository;

import com.wevioo.parametrage.entities.Fond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wevioo.parametrage.entities.Modalite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModaliteRepository  extends PagingAndSortingRepository<Modalite, Long> {
   /* String FILTER_FONDS_ON_FIRST_NAME_QUERY = "select f from Modalite f where UPPER(f.abrevModalite) like CONCAT('%',UPPER(?1),'%')";
    @Query(FILTER_FONDS_ON_FIRST_NAME_QUERY)
    Page<Modalite> findByFirstNameLike(String firstNameFilter, Pageable pageable);*/


}
