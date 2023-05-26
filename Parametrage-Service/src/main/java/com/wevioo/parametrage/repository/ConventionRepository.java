package com.wevioo.parametrage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wevioo.parametrage.entities.Convention;


@Repository
public interface ConventionRepository extends JpaRepository<Convention, Long> {


}
