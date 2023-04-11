package com.wevioo.parametrage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.parametrage.entities.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {


}
