package com.wevioo.parametrage.repository;

import com.wevioo.parametrage.entities.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    
    // Add this method to perform the custom query
    @Query("SELECT p FROM Partenaire p WHERE p.numFax = :numFax AND p.idPartenaire <> :id")
    Partenaire findByNumFaxExceptId(String numFax, Long id);
    // Add this method to perform the custom query
    @Query("SELECT p FROM Partenaire p WHERE p.mail = :mail AND p.idPartenaire <> :id")
    Partenaire findByMailExceptId(String mail, Long id);
    // Add this method to perform the custom query
    @Query("SELECT p FROM Partenaire p WHERE p.site = :site AND p.idPartenaire <> :id")
	Partenaire findBySiteExceptId(String site, Long id);
    @Query("SELECT p FROM Partenaire p WHERE p.numTelephone = :numTelephone AND p.idPartenaire <> :id")
	Partenaire findByNumTelephoneExceptId(String numTelephone, Long id);
    
    // Add this method to perform the custom query
    @Query("SELECT p FROM Partenaire p WHERE p.numFax = :numFax")
    Partenaire findByNumFax(String numFax);
    // Add this method to perform the custom query
    @Query("SELECT p FROM Partenaire p WHERE p.mail = :mail")
    Partenaire findByMail(String mail);
    // Add this method to perform the custom query
    @Query("SELECT p FROM Partenaire p WHERE p.site = :site")
	Partenaire findBySite(String site);
    @Query("SELECT p FROM Partenaire p WHERE p.numTelephone = :numTelephone")
	Partenaire findByNumTelephone(String numTelephone);
    
    @Query("SELECT p FROM Partenaire p WHERE p.abrevPartenaire = :abrevPartenaire AND p.idPartenaire <> :id")
	Partenaire findByAbrevPartenaireExceptId(String abrevPartenaire, Long id);
    
    
    Partenaire findByAbrevPartenaire(String abrevPartenaire);
}
