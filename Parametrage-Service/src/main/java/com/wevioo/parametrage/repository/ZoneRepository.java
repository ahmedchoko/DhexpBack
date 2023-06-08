package com.wevioo.parametrage.repository;


import com.wevioo.parametrage.entities.Delegation;
import com.wevioo.parametrage.entities.Gouvernorat;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.parametrage.entities.Zone;

import java.util.List;
import java.util.Set;

@Repository
public interface ZoneRepository extends PagingAndSortingRepository<Zone, Long> {

List<Zone> findByNomZone(String nom);
List<Zone> findByNomArabeZone(String nom);
List<Zone> findByCodeZone(String nom);

}
