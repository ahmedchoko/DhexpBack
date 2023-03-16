package com.wevioo.parametrage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wevioo.parametrage.entities.Convention;
import com.wevioo.parametrage.entities.Modalite;

public interface ConventionRepository extends JpaRepository<Convention, Long> {


}
