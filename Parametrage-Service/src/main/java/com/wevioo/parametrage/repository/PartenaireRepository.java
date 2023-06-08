package com.wevioo.parametrage.repository;

import com.wevioo.parametrage.entities.Partenaire;
import com.wevioo.parametrage.entities.StopLoss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Long>, JpaSpecificationExecutor<Partenaire> {

    /**
     * Retrieves the number of partners by partner type.
     *
     * @return A list of Object arrays containing the partner type and the count of partners
     */
    @Query("SELECT p.typePartenaire, COUNT(p) FROM Partenaire p GROUP BY p.typePartenaire")
    List<Object[]> getNobrePartenaireParType();

    /**
     * Retrieves the total number of partners.
     *
     * @return The total number of partners
     */
    @Query("SELECT COUNT(p.idPartenaire) FROM Partenaire p")
    int getNobreTotalPartenaire();

}
