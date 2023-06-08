package com.wevioo.parametrage.repository;

import com.wevioo.parametrage.entities.Modalite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModaliteRepository extends PagingAndSortingRepository<Modalite, Long> {

    /**
     * Retrieves a summary of modalities by request type and treasury.
     *
     * @return A list of Object arrays containing the month of signature, nature of the request, and average treasury
     */
    @Query("SELECT MONTH(c.dateSignature), m.natureDemande, Avg(f.tresorerieFond) AS totalTresorerie FROM Convention c JOIN c.modalite m JOIN m.fond f GROUP BY MONTH(c.dateSignature), m.natureDemande, f.tresorerieFond")
    List<Object[]> findModaliteSummaryByDemandeTypeAndTresorie();
    List<Modalite> findByAbrevModalite(String abrev);
    List<Modalite> findByNomArabeModalite(String nom);
    List<Modalite> findByNomCompletModalite(String abrev);

}
