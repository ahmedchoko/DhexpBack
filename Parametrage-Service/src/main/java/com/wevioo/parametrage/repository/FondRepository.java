package com.wevioo.parametrage.repository;

import com.wevioo.parametrage.entities.Modalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.wevioo.parametrage.entities.Fond;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FondRepository extends JpaRepository<Fond, Long>, JpaSpecificationExecutor<Fond> {

    /**
     * Retrieves a list of non-archived funds.
     *
     * @return A list of non-archived Fond objects
     */
    String FILTER_ARCHIVED_FONDS = "select f from Fond f where UPPER(f.statut) <> UPPER('archive')";
    @Query(FILTER_ARCHIVED_FONDS)
    List<Fond> getNonArchivedFonds();

    /**
     * Retrieves the total treasury of funds by sector.
     *
     * @return A list of Object arrays containing the sector name and the total treasury amount
     */
    @Query("SELECT s.name, SUM(f.tresorerieFond) AS total_tresoriefond FROM Fond f JOIN f.secteurs s GROUP BY s.name")
    List<Object[]> FondTresorieBySecteur();

    /**
     * Retrieves the count of funds by status.
     *
     * @return A list of Object arrays containing the fund status and the count
     */
    @Query("SELECT f.statut, Count(f.idFond) FROM Fond f GROUP BY f.statut")
    List<Object[]> FondCountByStatus();

    /**
     * Retrieves the total treasury of all funds.
     *
     * @return The total treasury amount
     */
    @Query("SELECT Sum(f.tresorerieFond) FROM Fond f")
    float FondTotal();

    List<Fond> findByModalites(Modalite mod);
}
