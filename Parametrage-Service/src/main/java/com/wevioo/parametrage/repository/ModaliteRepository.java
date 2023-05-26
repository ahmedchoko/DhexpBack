package com.wevioo.parametrage.repository;



import java.util.List;


import com.wevioo.parametrage.entities.Modalite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModaliteRepository  extends PagingAndSortingRepository<Modalite, Long> {
   /* String FILTER_FONDS_ON_FIRST_NAME_QUERY = "select f from Modalite f where UPPER(f.abrevModalite) like CONCAT('%',UPPER(?1),'%')";
    @Query(FILTER_FONDS_ON_FIRST_NAME_QUERY)
    Page<Modalite> findByFirstNameLike(String firstNameFilter, Pageable pageable);*/

	@Query("SELECT MONTH(c.dateSignature),m.natureDemande, Avg(f.tresorerieFond) AS totalTresorerie FROM Convention c JOIN c.modalite m JOIN m.fond f GROUP BY MONTH(c.dateSignature), m.natureDemande, f.tresorerieFond")
	List<Object[]> findModaliteSummaryByDemandeTypeAndTresorie();

}
