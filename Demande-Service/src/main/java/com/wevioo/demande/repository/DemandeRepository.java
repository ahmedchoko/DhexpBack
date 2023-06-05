package com.wevioo.demande.repository;

import java.util.List;

import com.wevioo.parametrage.entities.Fond;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.wevioo.demande.entities.Demande;
import org.springframework.stereotype.Repository;

@Repository

public interface DemandeRepository extends JpaRepository<Demande, Long> , JpaSpecificationExecutor<Demande>
{
    String EN_COURS = "select d from Demande d WHERE UPPER(d.statut) like UPPER('ENCOURS')";
    @Query(EN_COURS)
    Page <Demande> getDemandesEncours(Pageable pageable);
    String FINIS = "select d from Demande d WHERE UPPER(d.statut) like UPPER('VALIDEE')";
    @Query(FINIS)
    Page <Demande> getDemandesValidees(Pageable pageable);
}
